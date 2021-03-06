package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookRankHistoryDao;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookRankHistoryConditions;
import com.dreamy.enums.BookRankTrendEnums;
import com.dreamy.service.iface.ipcool.BookRankHistoryService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookRankHistoryServiceImpl implements BookRankHistoryService {
    @Resource
    BookRankHistoryDao bookRankHistoryDao;

    @Override
    public void save(BookRankHistory bookRankHistory) {
        bookRankHistoryDao.save(bookRankHistory);
    }

    @Override
    public List<BookRankHistory> getList(BookRankHistory bookRankHistory, Page page) {

        Map<String, Object> params = BeanUtils.toQueryMap(bookRankHistory);
        BookRankHistoryConditions conditions = new BookRankHistoryConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(bookRankHistoryDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookRankHistoryDao.selectByExample(conditions);
    }

    @Override
    public List<BookRankHistory> getByBookIdAndType(Integer bookId, Integer type, Page page) {
        BookRankHistoryConditions conditions = new BookRankHistoryConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId).andTypeEqualTo(type);
        conditions.setOrderByClause("created_at desc");

        conditions.setPage(page);

        return bookRankHistoryDao.selectByExample(conditions);
    }

    @Override
    public BookRankHistory getTopHistoryByBookIdAndType(Integer bookId, Integer type) {
        BookRankHistory bookRankHistory = new BookRankHistory();

        BookRankHistoryConditions conditions = new BookRankHistoryConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId).andTypeEqualTo(type);

        Page p = new Page();
        p.setPageSize(1);

        conditions.setPage(p);
        conditions.setOrderByClause("rank_index desc");

        List<BookRankHistory> bookRankHistoryList = bookRankHistoryDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(bookRankHistoryList)) {
            bookRankHistory = bookRankHistoryList.get(0);
        }

        return bookRankHistory;
    }

    @Override
    public Integer getTrendFlag(Integer currentIndex, Integer historyTopIndex) {
        Integer res = BookRankTrendEnums.keep.getType();
        if (currentIndex > historyTopIndex) {
            res = BookRankTrendEnums.up.getType();
        } else if (currentIndex < historyTopIndex) {
            res = BookRankTrendEnums.down.getType();
        }

        return res;
    }

    @Override
    public Integer delByBookIdAndDate(Integer bookId, Date date) {
        BookRankHistoryConditions conditions = new BookRankHistoryConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId).andCreatedAtEqualTo(TimeUtils.getDate(date));
        return bookRankHistoryDao.deleteByExample(conditions);
    }
}
