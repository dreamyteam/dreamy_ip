package com.dreamy.admin.tasks.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.IpTypeEnums;
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

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/31
 * Time: 上午11:57
 */
@Component
public class FlushBookRankToDb {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlushBookRankToDb.class);

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookRankService bookRankService;

    @Autowired
    private BookRankHistoryService bookRankHistoryService;

    @Scheduled(cron = "0 42 00 * * ?")
    public void run() {
        Page page = new Page();
        page.setPageSize(500);
        int currentPage = 1;
        Boolean isLoop = true;
        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.chuban.getType());
                if (CollectionUtils.isNotEmpty(bookViewList)) {
                    for (BookView bookView : bookViewList) {
                        updateRank(bookView);
                    }

                    currentPage++;
                } else {
                    isLoop = false;
                }
            } catch (Exception e) {
                LOGGER.error("## flush rand redis to db failed ", e);
                break;
            }
        }
    }


    /**
     * 更新排行榜
     *
     * @param bookView
     */
    public void updateRank(BookView bookView) {
        updateRank(bookView, BookRankEnums.composite.getCacheKey(), BookIndexTypeEnums.composite.getType(), bookView.getCompositeIndex());
        updateRank(bookView, BookRankEnums.develop.getCacheKey(), BookIndexTypeEnums.develop.getType(), bookView.getDevelopIndex());
        updateRank(bookView, BookRankEnums.propagation.getCacheKey(), BookIndexTypeEnums.propagate.getType(), bookView.getPropagateIndex());
        updateRank(bookView, BookRankEnums.hot.getCacheKey(), BookIndexTypeEnums.hot.getType(), bookView.getHotIndex());
    }

    private void updateRank(BookView bookView, String cacheKey, Integer rankType, Integer index) {
        try {
            Integer bookId = bookView.getBookId();

            Long rankNum = redisClientService.reverseZrank(cacheKey, bookView.getBookId().toString());
            if (rankNum != null) {
                rankNum++;
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
            LOGGER.error("update rank failed :id=" + bookView.getId() + ":type=" + rankType, e);
        }
    }
}
