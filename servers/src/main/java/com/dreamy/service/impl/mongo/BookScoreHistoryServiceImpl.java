package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.history.BookScoreHistory;
import com.dreamy.mogodb.dao.BookScoreHistoryDao;
import com.dreamy.service.iface.mongo.BookScoreHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Service
public class BookScoreHistoryServiceImpl implements BookScoreHistoryService {

    @Autowired
    BookScoreHistoryDao bookScoreHistoryDao;

    @Override
    public void updateInser(BookScoreHistory bookScoreHistory) {
        bookScoreHistoryDao.updateInser(bookScoreHistory);
    }

    @Override
    public void saveByRecord(BookScoreHistory bookScoreHistory) {
        bookScoreHistoryDao.save(bookScoreHistory);
    }

    @Override
    public List<BookScoreHistory> getByBookId(Integer bookId) {
        return bookScoreHistoryDao.queryByBookId(bookId);
    }
}
