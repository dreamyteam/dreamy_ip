package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
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
    private BookIndexHistoryService bookIndexHistoryService;


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

    /**
     * 获取新的火热指数
     *
     * @param bookView
     */
    private Integer getNewHotIndex(BookView bookView) {
        try {
            String hotIndexStr = bookScoreService.getBookHotIndexByBookId(bookView.getBookId());
            Integer hotIndex = Integer.parseInt(hotIndexStr);
            if (hotIndex > 0) {
                return hotIndex;
            }
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
            Integer index = Integer.parseInt(propagateIndex);
            if (index > 0) {
                return index;
            }
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
            Integer index = Integer.parseInt(reputationIndex);
            if (index > 0) {
                return index;
            }
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
            Integer index = Integer.parseInt(developIndex);
            if (index > 0) {
                return index;
            }
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
