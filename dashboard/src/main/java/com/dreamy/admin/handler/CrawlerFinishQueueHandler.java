package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.admin.IndexCalculation.book.chuban.ChubanBookSourceBaseHandler;
import com.dreamy.admin.IndexCalculation.book.chuban.ChubanManage;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.IndexRankEnums.chuban.ChubanHotIndexRandEnums;
import com.dreamy.enums.IndexRankEnums.chuban.ChubanPropagationIndexRandEnums;
import com.dreamy.enums.IndexRankEnums.chuban.ChubanReputationIndexRandEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.*;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/27
 * Time: 上午10:48
 */
@Component
public class CrawlerFinishQueueHandler extends AbstractQueueHandler {
    private static final Logger Log = LoggerFactory.getLogger(CrawlerFinishQueueHandler.class);

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private PeopleChartService peopleChartService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private ChubanManage chubanManage;


    @Override
    public void consume(JSONObject jsonObject) {
        String bookIdStr = jsonObject.getString("bookId");
        Log.info("starting book over : " + bookIdStr);
        if (StringUtils.isNotEmpty(bookIdStr)) {
            Integer bookId = Integer.parseInt(bookIdStr);
            try {
                update(bookId);
            } catch (Exception e) {
                Log.error("update rank failed :" + bookIdStr, e);
            }
        }

    }

    public void update(Integer bookId) {
        BookView bookView = bookViewService.getByBookId(bookId);

        if (bookView != null) {
//            updateChuban(bookView);
        }
    }

    /**
     * 出版文学更新
     *
     * @param bookId
     * @param bookView
     */
    public void updateChuban(BookView bookView) {
        Integer bookId = bookView.getBookId();
        List<BookInfo> bookInfoList = bookInfoService.getListByIpId(bookId);
        if (CollectionUtils.isNotEmpty(bookInfoList)) {

            //计算指数
            Integer hotIndex = getNewHotIndex(bookView);
            Integer propagationIndex = getNewPropogationIndex(bookView);
            Integer reputationIndex = getNewReputationIndex(bookView);

            bookView.hotIndex(hotIndex);
            bookView.propagateIndex(propagationIndex);
            bookView.reputationIndex(reputationIndex);

            Integer developIndex = getNewDevelopIndex(bookView);
            bookView.developIndex(developIndex);
//
            Integer compositeIndex = getNewCompositeIndex(bookView);
            bookView.compositeIndex(compositeIndex);

            //更新指数
            bookViewService.update(bookView);
            updateHistoryIndex(bookView);
//
            //指数写入到redis用于排名
            //updateRank(bookView);

        }
    }


    /**
     * 获取新的火热指数
     *
     * @param bookView
     */
    private Integer getNewHotIndex(BookView bookView) {
        Map<Integer, ChubanBookSourceBaseHandler> chubanBookSourceHandlerMap = chubanManage.getHandlerMap();
        try {
            Integer index = 0;
            for (ChubanBookSourceBaseHandler chubanBookSourceHandler : chubanBookSourceHandlerMap.values()) {
                index += chubanBookSourceHandler.getHotIndex(bookView);
            }
            ChubanHotIndexRandEnums[] chubanHotIndexRandEnumses = ChubanHotIndexRandEnums.values();
            for (Integer i = 0, length = chubanHotIndexRandEnumses.length; i < length; i++) {
                ChubanHotIndexRandEnums chubanHotIndexRandEnums = chubanHotIndexRandEnumses[i];
                Integer start = chubanHotIndexRandEnums.getStart();
                Integer end = chubanHotIndexRandEnums.getEnd();
                if (index >= start && index <= end) {
                    ChubanHotIndexRandEnums nextChubanHotIndexRandEnums = chubanHotIndexRandEnumses[i + 1];
                    Double scoreGap = (nextChubanHotIndexRandEnums.getScore() - chubanHotIndexRandEnums.getScore()) * 1.0;

                    Double temp = tailScore(index, start, end, scoreGap);
                    index = chubanHotIndexRandEnums.getScore() + temp.intValue();
                    break;
                }
            }
            return index;
        } catch (Exception e) {
            Log.error("update hot index failed :" + bookView.getId(), e);
        }
        return bookView.getHotIndex();
    }

    /**
     * 获取新的传播指数
     *
     * @param bookView
     */
    private Integer getNewPropogationIndex(BookView bookView) {
        Map<Integer, ChubanBookSourceBaseHandler> chubanBookSourceHandlerMap = chubanManage.getHandlerMap();
        try {
            Integer index = 0;
            for (ChubanBookSourceBaseHandler chubanBookSourceHandler : chubanBookSourceHandlerMap.values()) {
                Integer temp = chubanBookSourceHandler.getPropagationIndex(bookView);
                index += temp;
            }

            ChubanPropagationIndexRandEnums[] indexRandEnumses = ChubanPropagationIndexRandEnums.values();
            for (Integer i = 0, length = indexRandEnumses.length; i < length; i++) {
                ChubanPropagationIndexRandEnums indexRankEnum = indexRandEnumses[i];
                Integer start = indexRankEnum.getStart();
                Integer end = indexRankEnum.getEnd();
                if (index >= start && index <= end) {
                    ChubanPropagationIndexRandEnums nextIndexRankEnum = indexRandEnumses[i + 1];
                    Double scoreGap = (nextIndexRankEnum.getScore() - indexRankEnum.getScore()) * 1.0;

                    Double temp = tailScore(index, start, end, scoreGap);
                    index = indexRankEnum.getScore() + temp.intValue();
                    break;
                }
            }

            return index;
        } catch (Exception e) {
            Log.error("update propagation index failed :" + bookView.getId(), e);
        }

        return bookView.getPropagateIndex();
    }

    /**
     * 获取新的口碑指数
     *
     * @param bookView
     */
    private Integer getNewReputationIndex(BookView bookView) {
        Map<Integer, ChubanBookSourceBaseHandler> chubanBookSourceHandlerMap = chubanManage.getHandlerMap();

        try {
            Integer index = 0;
            for (ChubanBookSourceBaseHandler chubanBookSourceHandler : chubanBookSourceHandlerMap.values()) {
                Double temp = chubanBookSourceHandler.getReputationIndex(bookView) * 1.0 / 100;
                index += temp.intValue();
            }

            ChubanReputationIndexRandEnums[] indexRandEnumses = ChubanReputationIndexRandEnums.values();
            for (Integer i = 0, length = indexRandEnumses.length; i < length; i++) {
                ChubanReputationIndexRandEnums indexRankEnum = indexRandEnumses[i];
                Integer start = indexRankEnum.getStart();
                Integer end = indexRankEnum.getEnd();
                if (index >= start && index <= end) {
                    ChubanReputationIndexRandEnums nextIndexRankEnum = indexRandEnumses[i + 1];
                    Double scoreGap = (nextIndexRankEnum.getScore() - indexRankEnum.getScore()) * 1.0;

                    Double temp = tailScore(index, start, end, scoreGap);
                    index = indexRankEnum.getScore() + temp.intValue();
                    break;
                }
            }
            return index;
        } catch (Exception e) {
            Log.error("update reputation failed :" + bookView.getId(), e);
        }

        return bookView.getReputationIndex();
    }

    /**
     * 获取新的潜力指数
     *
     * @param bookView
     */
    private Integer getNewDevelopIndex(BookView bookView) {
        try {
            Double developScore = 10.0;
            Integer hotIndex = bookView.getHotIndex();
            Integer propagationIndex = bookView.getPropagateIndex();
            developScore += (hotIndex + propagationIndex) * 0.5;

            List<PeopleChart> peopleChartList = peopleChartService.getListByBookId(bookView.getBookId());
            if (CollectionUtils.isNotEmpty(peopleChartList)) {
                PeopleChart peopleChart = peopleChartList.get(0);
                Double sexScore = 15 * peopleChart.getAgeFirst() + 23 * peopleChart.getAgeScond() + 28 * peopleChart.getAgeThird() + 16 * peopleChart.getAgeFourth() + 8 * peopleChart.getAgeFifth();
                if (sexScore > 0) {
                    developScore *= sexScore / (20.512);
                }
            }

            return developScore.intValue();

        } catch (Exception e) {
            Log.error("update develop index failed :" + bookView.getId(), e);
        }

        return bookView.getDevelopIndex();
    }


    /**
     * 获取新的综合指数
     *
     * @param bookView
     */
    private Integer getNewCompositeIndex(BookView bookView) {
        try {
            Integer hotIndex = bookView.getHotIndex();
            Integer propagationIndex = bookView.getPropagateIndex();
            Integer reputationIndex = bookView.getReputationIndex();
            Integer developIndex = bookView.getDevelopIndex();

            Double compositeIndex = 0.3 * hotIndex + 0.25 * propagationIndex + 0.3 * reputationIndex + 0.15 * developIndex;

            Integer index = compositeIndex.intValue();
            if (index > 0) {
                return index;
            }
        } catch (Exception e) {
            Log.error("update composite index failed :" + bookView.getId(), e);
        }

        return bookView.getCompositeIndex();
    }

    /**
     * @param bookView
     */
    private void updateHistoryIndex(BookView bookView) {
        bookIndexHistoryService.delByBookIdAndDate(bookView.getBookId(), new Date());
        BookIndexHistory history = new BookIndexHistory();
        history.setHotIndex(bookView.getHotIndex());
        history.setActivityIndex(bookView.getActivityIndex());
        history.setCompositeIndex(bookView.getCompositeIndex());
        history.setPropagateIndex(bookView.getPropagateIndex());
        history.setDevelopIndex(bookView.getDevelopIndex());
        history.setBookId(bookView.getBookId());
        history.setStatus(1);
        bookIndexHistoryService.save(history);
    }

    public void updateRank(BookView bookView) {
        redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.develop.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.propagation.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.hot.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());
    }


    private Double tailScore(Integer index, Integer start, Integer end, Double scoreGap) {
        Double temp = (index - start) * (scoreGap / (end - start));
        if (temp < 1.0) {
            temp = Math.random() * 10;
        } else {
            temp = temp * 1.0012345;
        }

        return temp;
    }

}
