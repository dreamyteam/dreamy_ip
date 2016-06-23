package com.dreamy.admin.tasks.rank.net;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.BookViewCalculateResult;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.enums.config.ChubanBookLevelConfigEnums;
import com.dreamy.enums.config.NetBookLevelConfigEnums;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookRankHistoryService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/31
 * Time: 上午11:57
 */
@Component
public class FlushNetBookRankToDb {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlushNetBookRankToDb.class);

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookRankService bookRankService;

    @Autowired
    private BookRankHistoryService bookRankHistoryService;


    @Scheduled(cron = "0 40 3 * * ?")
    public void run() {
        Page page = new Page();
        page.setPageSize(500);
        int currentPage = 1;
        Boolean isLoop = true;
        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.net.getType());
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
        updateRank(bookView, BookRankEnums.composite_net.getCacheKey(), BookIndexTypeEnums.composite.getType(), bookView.getCompositeIndex());
        updateRank(bookView, BookRankEnums.develop_net.getCacheKey(), BookIndexTypeEnums.develop.getType(), bookView.getDevelopIndex());
        updateRank(bookView, BookRankEnums.propagation_net.getCacheKey(), BookIndexTypeEnums.propagate.getType(), bookView.getPropagateIndex());
        updateRank(bookView, BookRankEnums.hot_net.getCacheKey(), BookIndexTypeEnums.hot.getType(), bookView.getHotIndex());
        updateRank(bookView, BookRankEnums.activity_net.getCacheKey(), BookIndexTypeEnums.active.getType(), bookView.getActivityIndex());
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
                    rank.source(bookView.getType());

                    bookRankService.save(rank);
                }

                BookRankHistory rankHistory = new BookRankHistory();
                rankHistory.bookId(bookView.getBookId());
                rankHistory.rank(rankNum.intValue());
                rankHistory.type(rankType);
                rankHistory.rankIndex(index);
                rankHistory.source(bookView.getType());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -1);
                rankHistory.createdAt(TimeUtils.getDate(calendar.getTime()));

                bookRankHistoryService.save(rankHistory);
            }

        } catch (Exception e) {
            LOGGER.error("update rank failed :id=" + bookView.getId() + ":type=" + rankType, e);
        }
    }


    public Integer getIndex() {

        Integer showIndex = 0;
        String chubanBookTotalCacheKey = "net_book_total_num";
        Long totalNum = redisClientService.getNumber(chubanBookTotalCacheKey);
        if (totalNum == null || totalNum == 0) {
            totalNum = (long) bookViewService.getTotalCountByType(IpTypeEnums.net.getType());
            redisClientService.setNumber(chubanBookTotalCacheKey, totalNum);
        }

        List<Integer> rankBottomList = new LinkedList<>();
        if (totalNum > 0) {
            NetBookLevelConfigEnums[] netBookLevelConfigEnumses = NetBookLevelConfigEnums.values();
            Integer length = netBookLevelConfigEnumses.length;
            Double percent = 0.0;

            for (Integer i = 0; i < length; i++) {
                percent += netBookLevelConfigEnumses[i].getPercent();
                Double rankLimit = totalNum * percent * 0.01;
                Integer rankBottom = (int) Math.ceil(rankLimit);
                rankBottomList.add(rankBottom);
            }

            List<Integer> scoresList = new LinkedList<>();
            Page page = new Page();
            page.setPageSize(1);
            page.setCurrentPage(1);
            List<BookViewCalculateResult> firstTemp = bookViewService.getCalculateResByPageAndOrder(page, "composite_index asc");
            scoresList.add(firstTemp.get(0).getCompositeIndex());
            for (Integer num : rankBottomList) {
                page.setCurrentPage(num);
                List<BookViewCalculateResult> temp = bookViewService.getCalculateResByPageAndOrder(page, "composite_index asc");
                if (CollectionUtils.isNotEmpty(temp)) {
                    scoresList.add(temp.get(0).getCompositeIndex());
                }
            }
            System.err.println("111");
        }

        return showIndex;
    }
}
