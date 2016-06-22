package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.admin.IndexCalculation.book.net.NetBookSourceBaseHandler;
import com.dreamy.admin.IndexCalculation.book.net.NetManage;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.IndexRankEnums.net.NetActivityRandEnums;
import com.dreamy.enums.IndexRankEnums.net.NetHotIndexRandEnums;
import com.dreamy.enums.IndexRankEnums.net.NetPropagationIndexRandEnums;
import com.dreamy.mogodb.beans.history.TieBaHistory;
import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.PeopleChartService;
import com.dreamy.service.iface.mongo.TieBaHistoryService;
import com.dreamy.service.iface.mongo.TieBaService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CrawlerNetbookFinishQueueHandler extends AbstractQueueHandler {
    private static final Logger Log = LoggerFactory.getLogger(CrawlerNetbookFinishQueueHandler.class);


    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;


    @Autowired
    private BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private TieBaService tieBaService;

    @Autowired
    private TieBaHistoryService tieBaHistoryService;

    @Autowired
    private NetManage netManage;

    @Autowired
    private PeopleChartService peopleChartService;


    @Override
    public void consume(JSONObject jsonObject) {
        String bookIdStr = jsonObject.getString("bookId");
        Log.info("starting book over : " + bookIdStr);
        if (StringUtils.isNotEmpty(bookIdStr)) {
            Integer bookId = Integer.parseInt(bookIdStr);
            try {
                BookView bookView = bookViewService.getByBookId(bookId);
                updateNet(bookView);
            } catch (Exception e) {
                Log.error("update rank failed :" + bookIdStr, e);
            }
        }

    }


    /**
     * 网络文学更新
     *
     * @param bookView
     */
    public void updateNet(BookView bookView) {
        Integer hotIndex = getNewHotIndex(bookView);
        Integer propagationIndex = getNewPropogationIndex(bookView);
        Integer activeIndex = getNewActiveIndex(bookView);


        bookView.hotIndex(hotIndex);
        bookView.propagateIndex(propagationIndex);
        bookView.activityIndex(activeIndex);

        Integer developIndex = getNewDevelopIndex(bookView);
        bookView.developIndex(developIndex);

        Integer compositeIndex = getNewCompositeIndex(bookView);
        bookView.compositeIndex(compositeIndex);

        //更新存取计算结果
//        BookViewCalculateResult result = new BookViewCalculateResult();
//        result.bookId(bookView.getBookId()).name(bookView.getName())
//                .type(bookView.getType())
//                .compositeIndex(compositeIndex)
//                .hotIndex(hotIndex)
//                .propagateIndex(propagationIndex)
//                .activityIndex(activeIndex)
//                .developIndex(developIndex);
//
//        bookViewService.saveCalculateRes(result);
        bookViewService.update(bookView);
        updateHistoryIndex(bookView);

        //指数写入到redis用于排名
        updateRank(bookView);
    }


    /**
     * 获取新的火热指数
     *
     * @param bookView
     */
    private Integer getNewHotIndex(BookView bookView) {
        Map<Integer, NetBookSourceBaseHandler> netBookSourceBaseHandlerMap = netManage.getHandlerMap();
        try {
            Integer index = 0;
            for (NetBookSourceBaseHandler netBookSourceBaseHandler : netBookSourceBaseHandlerMap.values()) {
                Integer temp = netBookSourceBaseHandler.getHotIndex(bookView);
                index += temp;
            }

            NetHotIndexRandEnums[] netHotIndexRandEnumses = NetHotIndexRandEnums.values();
            for (Integer i = 0, length = netHotIndexRandEnumses.length; i < length; i++) {
                NetHotIndexRandEnums netHotIndexRandEnums = netHotIndexRandEnumses[i];
                Integer start = netHotIndexRandEnums.getStart();
                Integer end = netHotIndexRandEnums.getEnd();
                if (index >= start && index <= end) {
                    NetHotIndexRandEnums nextChubanHotIndexRandEnums = netHotIndexRandEnumses[i + 1];
                    Double scoreGap = (nextChubanHotIndexRandEnums.getScore() - netHotIndexRandEnums.getScore()) * 1.0;

                    Double temp = tailScore(index,start,end,scoreGap);
                    index = netHotIndexRandEnums.getScore() + temp.intValue();
                    break;
                }
            }

            if (index == 0) {
                Double temp = Math.random() * 10;
                index = temp.intValue();
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
        Map<Integer, NetBookSourceBaseHandler> netBookSourceBaseHandlerMap = netManage.getHandlerMap();
        try {
            Integer index = 0;
            for (NetBookSourceBaseHandler netBookSourceBaseHandler : netBookSourceBaseHandlerMap.values()) {
                Integer temp = netBookSourceBaseHandler.getPropagationIndex(bookView);
                index += temp;
            }

            NetPropagationIndexRandEnums[] indexRandEnumses = NetPropagationIndexRandEnums.values();
            for (Integer i = 0, length = indexRandEnumses.length; i < length; i++) {
                NetPropagationIndexRandEnums indexRankEnum = indexRandEnumses[i];
                Integer start = indexRankEnum.getStart();
                Integer end = indexRankEnum.getEnd();
                if (index >= start && index <= end) {
                    NetPropagationIndexRandEnums nextIndexRankEnum = indexRandEnumses[i + 1];
                    Double scoreGap = (nextIndexRankEnum.getScore() - indexRankEnum.getScore()) * 1.0;

                    Double temp = tailScore(index, start, end, scoreGap);
                    index = indexRankEnum.getScore() + temp.intValue();
                    break;
                }
            }

            if (index == 0) {
                Double temp = Math.random() * 10;
                index = temp.intValue();
            }

            return index;
        } catch (Exception e) {
            Log.error("update  propatation index failed :" + bookView.getId(), e);
        }
        return bookView.getPropagateIndex();
    }

    /**
     * 活跃指数
     *
     * @param bookView
     * @return
     */
    private Integer getNewActiveIndex(BookView bookView) {
        Integer index = 0;
        TieBa tieBa = tieBaService.getById(bookView.getBookId());
        if (tieBa != null) {
            TieBaHistory tieBaHistory = tieBaHistoryService.getLatestHistoryByBookId(bookView.getBookId());
            if (tieBaHistory != null) {
                Integer lastFollowNum = tieBaHistory.getFollowNum();
                Integer currentFollowNum = tieBa.getFollowNum();
                if ((lastFollowNum != null && lastFollowNum > 0) && (currentFollowNum != null && currentFollowNum > 0)) {
                    Integer temp = (1 + tieBa.getPostNum() / currentFollowNum - tieBaHistory.getPostNum() / lastFollowNum);
                    Integer rankNum = tieBa.getPopularitySort();
                    if (rankNum != null && rankNum > 0) {
                        index = (tieBa.getFollowNum() + tieBa.getFollowNum() / rankNum) * temp * temp;
                    }
                }
            } else {
                index = tieBa.getFollowNum();
                if (index == null || index == 0) {
                    Double randomIndex = Math.random() * 10;
                    index = randomIndex.intValue();
                }
            }
        }

        NetActivityRandEnums[] indexRandEnumses = NetActivityRandEnums.values();
        for (Integer i = 0, length = indexRandEnumses.length; i < length; i++) {
            NetActivityRandEnums indexRankEnum = indexRandEnumses[i];
            Integer start = indexRankEnum.getStart();
            Integer end = indexRankEnum.getEnd();
            if (index >= start && index <= end) {
                NetActivityRandEnums nextIndexRankEnum = indexRandEnumses[i + 1];
                Double scoreGap = (nextIndexRankEnum.getScore() - indexRankEnum.getScore()) * 1.0;

                Double temp = tailScore(index, start, end, scoreGap);
                index = indexRankEnum.getScore() + temp.intValue();
                break;
            }
        }

        return index;
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
            Integer activeIndex = bookView.getActivityIndex();
            Integer developIndex = bookView.getDevelopIndex();

            Double compositeIndex = 0.1 * (3 * (hotIndex + propagationIndex) + 2 * (activeIndex + developIndex));

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
        redisClientService.zadd(BookRankEnums.composite_net.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.develop_net.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.propagation_net.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.hot_net.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.activity_net.getCacheKey(), bookView.getActivityIndex(), bookView.getBookId().toString());
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
