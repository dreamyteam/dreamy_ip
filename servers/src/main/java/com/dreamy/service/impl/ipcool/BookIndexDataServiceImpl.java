package com.dreamy.service.impl.ipcool;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.dao.BookIndexDataDao;
import com.dreamy.service.iface.ipcool.BookIndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/3
 * Time: 下午2:41
 */
@Service
public class BookIndexDataServiceImpl implements BookIndexDataService {

    @Autowired
    private BookIndexDataDao bookIndexDataDao;

    @Override
    public List<BookIndexData> getByBookId(Integer bookId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(bookId);
        query.addCriteria(criteria);
        return bookIndexDataDao.queryList(query);
    }
}
