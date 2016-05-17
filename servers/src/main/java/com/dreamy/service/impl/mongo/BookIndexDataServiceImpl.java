package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.dao.BookIndexDataDao;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/5.
 */
@Service
public class BookIndexDataServiceImpl implements BookIndexDataService {
    @Autowired
    private BookIndexDataDao bookIndexDataDao;

    @Override
    public void updateInser(BookIndexData data) {
        bookIndexDataDao.updateInser(data);
    }

    @Override
    public BookIndexData getById(Integer id) {
         return bookIndexDataDao.getById(id);
    }

    @Override
    public List<BookIndexData> getByBookId(Integer bookId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(bookId);
        query.addCriteria(criteria);
        return bookIndexDataDao.queryList(query);
    }
}
