package com.dreamy.admin.handler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookRankHistoryService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
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
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookRankService bookRankService;

    @Autowired
    private BookRankHistoryService bookRankHistoryService;

    @Override
    public void consume(JSONObject jsonObject) {
        final String bookIdStr = jsonObject.getString("bookId");

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

                                //更新指数
                                updateHotIndex(bookView);
                                updatePropogationIndex(bookView);
                                updateReputationIndex(bookView);
                                updateDevelopIndex(bookView);
                                updateCompositeIndex(bookView);

                                BookView updatedBookView = bookViewService.getByBookId(bookView.getBookId());
                                updateRank(updatedBookView);
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
     * 更新火热指数
     *
     * @param bookView
     */
    private void updateHotIndex(BookView bookView) {
        try {
            String hotIndex = bookScoreService.getBookHotIndexByBookId(bookView.getBookId());
            bookView.hotIndex(Integer.parseInt(hotIndex));
            bookViewService.update(bookView);
        } catch (Exception e) {
            Log.error("update hot index failed :" + bookView.getId(), e);
        }
    }

    /**
     * 更新传播指数
     *
     * @param bookView
     */
    private void updatePropogationIndex(BookView bookView) {
        try {
            String propagateIndex = bookScoreService.getPropagateIndexByBookId(bookView.getBookId());
            bookView.propagateIndex(Integer.parseInt(propagateIndex));
            bookViewService.update(bookView);
        } catch (Exception e) {
            Log.error("update  propatation index failed :" + bookView.getId(), e);
        }
    }

    /**
     * 更新口碑指数
     *
     * @param bookView
     */
    private void updateReputationIndex(BookView bookView) {
        try {
            String reputationIndex = bookScoreService.getReputationIndexByBookId(bookView.getBookId());
            bookView.reputationIndex(Integer.parseInt(reputationIndex));
            bookViewService.update(bookView);
        } catch (Exception e) {
            Log.error("update reputation failed :" + bookView.getId(), e);
        }
    }

    /**
     * 更新潜力指数
     *
     * @param bookView
     */
    private void updateDevelopIndex(BookView bookView) {
        try {
            String developIndex = bookScoreService.getDevelopIndexByRecord(bookView);
            bookView.developIndex(Integer.parseInt(developIndex));
            bookViewService.update(bookView);
        } catch (Exception e) {
            Log.error("update develop index failed :" + bookView.getId(), e);
        }
    }


    /**
     * 更新综合指数
     *
     * @param bookView
     */
    private void updateCompositeIndex(BookView bookView) {
        try {
            Integer hotIndex = bookView.getHotIndex();
            Integer propagationIndex = bookView.getPropagateIndex();
            Integer reputationIndex = bookView.getReputationIndex();
            Integer developIndex = bookView.getDevelopIndex();

            Double compositeIndex = 0.1 * (3 * (hotIndex + propagationIndex) + 2 * (reputationIndex + developIndex));

            bookView.compositeIndex(compositeIndex.intValue());
            bookViewService.update(bookView);
        } catch (Exception e) {
            Log.error("update composite index failed :" + bookView.getId(), e);
        }
    }

    /**
     *  ##############  ##############  ##############  排名更新  ##############  ##############  ##############
     */
    /**
     * 分数更新完成之后，把对应的分数写入到
     *
     * @param bookView
     */
    private void updateRank(BookView bookView) {
        redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.develop.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.propagation.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
        redisClientService.zadd(BookRankEnums.hot.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());

        updateRank(bookView, BookRankEnums.composite.getCacheKey(), BookIndexTypeEnums.composite.getType(), bookView.getCompositeIndex());
        updateRank(bookView, BookRankEnums.develop.getCacheKey(), BookIndexTypeEnums.develop.getType(), bookView.getDevelopIndex());
        updateRank(bookView, BookRankEnums.propagation.getCacheKey(), BookIndexTypeEnums.propagate.getType(), bookView.getPropagateIndex());
        updateRank(bookView, BookRankEnums.hot.getCacheKey(), BookIndexTypeEnums.hot.getType(), bookView.getHotIndex());

    }

    private void updateRank(BookView bookView, String cacheKey, Integer rankType, Integer index) {
        try {
            Integer bookId = bookView.getBookId();

            redisClientService.zadd(cacheKey, index, bookView.getBookId().toString());
            Long rankNum = redisClientService.reverseZrank(cacheKey, bookView.getBookId().toString());
            if (rankNum != null) {
                BookRank bookRank = new BookRank();
                bookRank.bookId(bookId);
                BookRank rank = bookRankService.getByBookIdAndType(bookId, rankType);
                if (rank != null) {
                    rank.rank(rankNum.intValue());
                    rank.rankIndex(index);

                    bookRankService.updateByRecord(rank);
                } else {
                    rank = new BookRank();
                    rank.bookId(bookView.getBookId());
                    rank.rank(rankNum.intValue());
                    rank.type(rankType);
                    rank.rankIndex(index);
                    rank.name(bookView.getName());

                    bookRankService.save(rank);
                }

                BookRankHistory rankHistory = new BookRankHistory();
                rankHistory.bookId(bookView.getBookId());
                rankHistory.rank(rankNum.intValue());
                rankHistory.type(rankType);
                rankHistory.rankIndex(index);

                bookRankHistoryService.save(rankHistory);
            }

        } catch (Exception e) {
            Log.error("update rank failed :id=" + bookView.getId() + ":type=" + rankType, e);
        }
    }

}
