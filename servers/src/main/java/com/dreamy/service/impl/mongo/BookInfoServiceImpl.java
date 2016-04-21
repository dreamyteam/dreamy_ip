package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.MemberDao;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(BookInfo bookInfo) {
        bookInfoDao.save(bookInfo);
    }

    @Override
    public BookInfo queryById(Integer id) {
        return bookInfoDao.queryById(id);
    }
}
