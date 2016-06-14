package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.history.BookIndexDataHistory;
import com.dreamy.mogodb.dao.BookIndexDataHistoryDao;
import com.dreamy.service.iface.mongo.BookIndexDataHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Service
public class BookIndexDataHistoryServiceImpl implements BookIndexDataHistoryService {

    @Autowired
    BookIndexDataHistoryDao bookIndexDataHistoryDao;

    @Override
    public void updateInser(BookIndexDataHistory bookIndexDataHistory) {
        bookIndexDataHistoryDao.updateInser(bookIndexDataHistory);

    }

    @Override
    public void saveByRecord(BookIndexDataHistory bookIndexDataHistory) {
        bookIndexDataHistoryDao.save(bookIndexDataHistory);
    }

    @Override
    public List<BookIndexDataHistory> getByBookId(Integer bookId) {
        return bookIndexDataHistoryDao.queryByBookId(bookId);
    }
}
