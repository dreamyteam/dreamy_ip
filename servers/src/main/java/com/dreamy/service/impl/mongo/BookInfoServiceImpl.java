package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.MemberDao;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/21
 * Time: 下午12:08
 */

@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private BookInfoDao bookInfoDao;

    @Override
    public void saveByRecord(BookInfo bookInfo) {
        bookInfoDao.save(bookInfo);
    }

    @Override
    public BookInfo getById(Integer id) {
        return bookInfoDao.queryById(id);
    }

    @Override
    public void delById(Integer id) {
        bookInfoDao.deleteById(id);
    }

    @Override
    public List<BookInfo> getList(int ipId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("ipId").is(ipId);
        query.addCriteria(criteria);
        return bookInfoDao.queryList(query);

    }
}
