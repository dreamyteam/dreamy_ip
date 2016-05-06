package com.dreamy.admin.tasks.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookRankHistoryService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/5
 * Time: 下午6:48
 * <p/>
 * 小说综合排名
 */
@Component
public class BookRankCreateTask {
    private static final Logger log = LoggerFactory.getLogger(BookRankCreateTask.class);

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookRankService bookRankService;

    @Autowired
    private BookRankHistoryService bookRankHistoryService;

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void run() {
        Page page = new Page();
        page.setPageSize(1000);


        try {
            List<BookView> bookViewList = bookViewService.getListByPageAndOrder(page, "id asc");
            if (CollectionUtils.isEmpty(bookViewList)) {
                return;
            }

            for (BookView bookView : bookViewList) {
                redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
                redisClientService.zadd(BookRankEnums.develop.getCacheKey(), bookView.getDevelopIndex(), bookView.getBookId().toString());
                redisClientService.zadd(BookRankEnums.propagation.getCacheKey(), bookView.getPropagateIndex(), bookView.getBookId().toString());
                redisClientService.zadd(BookRankEnums.hot.getCacheKey(), bookView.getHotIndex(), bookView.getBookId().toString());
            }

            Map<Integer, Integer> compositeRankMap = bookRankService.getBookRankMapFromRedisByCacheKey(BookRankEnums.composite.getCacheKey());
            Map<Integer, Integer> developRankMap = bookRankService.getBookRankMapFromRedisByCacheKey(BookRankEnums.develop.getCacheKey());
            Map<Integer, Integer> propagationRankMap = bookRankService.getBookRankMapFromRedisByCacheKey(BookRankEnums.propagation.getCacheKey());
            Map<Integer, Integer> hotRankMap = bookRankService.getBookRankMapFromRedisByCacheKey(BookRankEnums.hot.getCacheKey());


            for (BookView bookView : bookViewList) {

                update(bookView, BookRankEnums.composite.getType(), compositeRankMap);
                update(bookView, BookRankEnums.develop.getType(), developRankMap);
                update(bookView, BookRankEnums.propagation.getType(), propagationRankMap);
                update(bookView, BookRankEnums.hot.getType(), hotRankMap);
            }
        } catch (Exception e) {
            log.error("book rank update failed", e);
        }
    }

    private void update(BookView bookView, Integer type, Map<Integer, Integer> rankMap) {
        if (CollectionUtils.isEmpty(rankMap)) {
            return;
        }

        try {
            BookRank bookRank = new BookRank();
            Integer bookId = bookView.getBookId();

            bookRank.bookId(bookId).type(type);
            Page tempPage = new Page();
            tempPage.setPageSize(1);
            List<BookRank> currentRankList = bookRankService.getList(bookRank, tempPage);

            bookRank.rankIndex(rankMap.get(bookId))
                    .rank(rankMap.get(bookId));

            if (CollectionUtils.isNotEmpty(currentRankList)) {
                BookRank currentRank = currentRankList.get(0);
                bookRankService.deleteById(currentRank.getId());

                BookRankHistory bookRankHistory = new BookRankHistory();
                bookRankHistory.bookId(currentRank.getBookId()).type(currentRank.getType()).rankIndex(currentRank.getRankIndex()).rank(currentRank.getRank());
                bookRankHistoryService.save(bookRankHistory);
            }

            bookRankService.save(bookRank);
        } catch (Exception e) {
            log.error("book rank type failed and the type is:" + type, e);
        }
    }
}
