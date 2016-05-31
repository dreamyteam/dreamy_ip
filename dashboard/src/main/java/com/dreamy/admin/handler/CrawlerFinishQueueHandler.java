package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.*;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
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
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookRankService bookRankService;

    @Autowired
    private BookRankHistoryService bookRankHistoryService;

    @Autowired
    private BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private QueueService queueService;

    @Value("${queue_book_waitting_rank_update}")
    private String bookRankWaittingUpdateQueue;

    @Override
    public void consume(JSONObject jsonObject) {
        final String bookIdStr = jsonObject.getString("bookId");
        Log.info("starting book over : " + bookIdStr);
        if (StringUtils.isNotEmpty(bookIdStr)) {
            try {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Integer bookId = Integer.parseInt(bookIdStr);
                        final BookView bookView = bookViewService.getByBookId(bookId);

                        if (bookView != null) {
                            List<BookInfo> bookInfoList = bookInfoService.getListByIpId(bookId);
                            if (CollectionUtils.isNotEmpty(bookInfoList)) {

                                //计算指数
                                Integer hotIndex = getNewHotIndex(bookView);
                                Integer propagationIndex = getNewPropogationIndex(bookView);
                                Integer reputationIndex = getNewReputationIndex(bookView);
                                Integer developIndex = getNewDevelopIndex(bookView);


                                bookView.hotIndex(hotIndex);
                                bookView.propagateIndex(propagationIndex);
                                bookView.reputationIndex(reputationIndex);
                                bookView.developIndex(developIndex);

                                Integer compositeIndex = getNewCompositeIndex(bookView);
                                bookView.compositeIndex(compositeIndex);

                                //更新指数
                                bookViewService.update(bookView);
                                updateHistoryIndex(bookView);

                                //指数写入到redis用于排名
                                updateRank(bookView);
                            }
                        }
                    }
                };

                //更新排名
                threadPoolTaskExecutor.execute(r);
            } catch (Exception e) {
                Log.error("update rank failed :" + bookIdStr, e);
            }
        }

    }

    /**
     * 获取新的火热指数
     *
     * @param bookView
     */
    private Integer getNewHotIndex(BookView bookView) {
        try {
            String hotIndex = bookScoreService.getBookHotIndexByBookId(bookView.getBookId());
            return Integer.parseInt(hotIndex);
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
        try {
            String propagateIndex = bookScoreService.getPropagateIndexByBookId(bookView.getBookId());
            return Integer.parseInt(propagateIndex);
        } catch (Exception e) {
            Log.error("update  propatation index failed :" + bookView.getId(), e);
        }
        return bookView.getPropagateIndex();
    }

    /**
     * 获取新的口碑指数
     *
     * @param bookView
     */
    private Integer getNewReputationIndex(BookView bookView) {
        try {
            String reputationIndex = bookScoreService.getReputationIndexByBookId(bookView.getBookId());
            return Integer.parseInt(reputationIndex);
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
            String developIndex = bookScoreService.getDevelopIndexByRecord(bookView);
            return Integer.parseInt(developIndex);
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

            Double compositeIndex = 0.1 * (3 * (hotIndex + propagationIndex) + 2 * (reputationIndex + developIndex));

            return compositeIndex.intValue();
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

    private void updateRank(BookView bookView) {
        redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.develop.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.propagation.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.hot.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());
    }

}
