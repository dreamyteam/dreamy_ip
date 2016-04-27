package com.dreamy.service.impl.mongo;

import com.dreamy.beans.Page;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.MemberDao;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public BookInfo getById(Integer id) {
        return bookInfoDao.queryById(id);
    }

    @Override
    public void delById(Integer id) {
        bookInfoDao.deleteById(id);
    }


}
