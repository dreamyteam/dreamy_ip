package com.dreamy.service.impl.mongo;

import com.dreamy.beans.Page;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.dao.BookInfoDao;
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
    private BookInfoDao bookInfoDao;

    @Override
    public void saveByRecord(BookInfo bookInfo) {
        bookInfoDao.save(bookInfo);
    }

    @Override
    public BookInfo getById(String id) {
        return bookInfoDao.queryById(id);
    }

    @Override
    public void delById(String id) {
        bookInfoDao.deleteById(id);
    }

    @Override
    public List<BookInfo> getListByIpId(int ipId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("ipId").is(ipId);
        query.addCriteria(criteria);
        return bookInfoDao.queryList(query);
    }

    @Override
    public List<BookInfo> getListByOrderAndPage(Page page, String order) {

        return null;
    }

    @Override
    public void updateInser(BookInfo bookInfo) {
        bookInfoDao.updateInser(bookInfo);
    }

    @Override
    public List<BookInfo> getListByISBN(String isbn) {
        Query query = new Query();
        Criteria criteria = Criteria.where("ISBN").is(isbn);
        query.addCriteria(criteria);
        return bookInfoDao.queryList(query);
    }

    @Override
    public void udpate(BookInfo bookInfo) throws Exception {
        bookInfoDao.updateMulti(bookInfo);
    }
}
