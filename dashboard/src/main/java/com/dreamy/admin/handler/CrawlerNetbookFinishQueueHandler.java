package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.admin.IndexCalculation.book.chuban.ChubanBookSourceBaseHandler;
import com.dreamy.admin.IndexCalculation.book.net.NetBookSourceBaseHandler;
import com.dreamy.admin.IndexCalculation.book.net.NetManage;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IndexRankEnums.chuban.ChubanHotIndexRandEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.history.TieBaHistory;
import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
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
    private BookInfoService bookInfoService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private RedisClientService redisClientService;


    @Autowired
    private BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private NetBookInfoService netBookInfoService;

    @Autowired
    private TieBaService tieBaService;

    @Autowired
    private TieBaHistoryService tieBaHistoryService;

    @Autowired
    private NetManage netManage;


    @Override
    public void consume(JSONObject jsonObject) {
//        String bookIdStr = jsonObject.getString("bookId");
//        Log.info("starting book over : " + bookIdStr);
//        if (StringUtils.isNotEmpty(bookIdStr)) {
//            Integer bookId = Integer.parseInt(bookIdStr);
//            try {
//                BookView bookView = bookViewService.getByBookId(bookId);
//                updateNet(bookView);
//            } catch (Exception e) {
//                Log.error("update rank failed :" + bookIdStr, e);
//            }
//        }

    }


    /**
     * 网络文学更新
     *
     * @param bookView
     */
    public void updateNet(BookView bookView) {
        Integer hotIndex = getNewHotIndex(bookView);
//        Integer propagationIndex = getNewPropogationIndex(bookView);
//        Integer activeIndex = getNewActiveIndex(bookView);


        bookView.hotIndex(hotIndex);
//        bookView.propagateIndex(propagationIndex);
//        bookView.activityIndex(activeIndex);
//
//        Integer developIndex = getNewDevelopIndex(bookView);
//        bookView.developIndex(developIndex);
//
//        Integer compositeIndex = getNewCompositeIndex(bookView);
//        bookView.compositeIndex(compositeIndex);

        //更新指数
        bookViewService.update(bookView);
//        updateHistoryIndex(bookView);

        //指数写入到redis用于排名
//            updateRank(bookView);

    }


    /**
     * 获取新的火热指数
     *
     * @param bookView
     */
    private Integer getNewHotIndex(BookView bookView) {
        Map<Integer, NetBookSourceBaseHandler> netBookSourceBaseHandlerMap = netManage.getHandlerMap();
        try {
            Integer index = bookView.getHotIndex();
            for (NetBookSourceBaseHandler netBookSourceBaseHandler : netBookSourceBaseHandlerMap.values()) {
                Integer temp = netBookSourceBaseHandler.getHotIndex(bookView);
                index += temp;
            }

            if (index == 0) {
                Double temp = Math.random() * 10;
                index = temp.intValue();
            }
//            ChubanHotIndexRandEnums[] chubanHotIndexRandEnumses = ChubanHotIndexRandEnums.values();
//            for (Integer i = 0, length = chubanHotIndexRandEnumses.length; i < length; i++) {
//                ChubanHotIndexRandEnums chubanHotIndexRandEnums = chubanHotIndexRandEnumses[i];
//                Integer start = chubanHotIndexRandEnums.getStart();
//                Integer end = chubanHotIndexRandEnums.getEnd();
//                if (index >= start && index <= end) {
//                    ChubanHotIndexRandEnums nextChubanHotIndexRandEnums = chubanHotIndexRandEnumses[i + 1];
//                    Double scoreGap = (nextChubanHotIndexRandEnums.getScore() - chubanHotIndexRandEnums.getScore()) * 1.0;
//
//                    Double temp = tailScore(index, start, end, scoreGap);
//                    index = chubanHotIndexRandEnums.getScore() + temp.intValue();
//                    break;
//                }
//            }
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
//        try {
//            String propagateIndex = bookScoreService.getPropagateIndexByBookId(bookView.getBookId());
//            Integer index = Integer.parseInt(propagateIndex);
//            if (index > 0) {
//                return index;
//            }
//        } catch (Exception e) {
//            Log.error("update  propatation index failed :" + bookView.getId(), e);
//        }
        return bookView.getPropagateIndex();
    }

    /**
     * 活跃指数
     *
     * @param bookView
     * @return
     */
    private Integer getNewActiveIndex(BookView bookView) {
        Integer index = 10;
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
//        try {
//            String developIndex = bookScoreService.getDevelopIndexByRecord(bookView);
//            Integer index = Integer.parseInt(developIndex);
//            if (index > 0) {
//                return index;
//            }
//        } catch (Exception e) {
//            Log.error("update develop index failed :" + bookView.getId(), e);
//        }

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
        redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.develop.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.propagation.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.hot.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());
    }

}
