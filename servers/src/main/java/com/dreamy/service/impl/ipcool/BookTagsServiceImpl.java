package com.dreamy.service.impl.ipcool;

import com.dreamy.dao.iface.ipcool.BookTagsDao;
import com.dreamy.dao.iface.ipcool.BookTagsMapDao;
import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsConditions;
import com.dreamy.domain.ipcool.BookTagsMap;
import com.dreamy.service.iface.ipcool.BookTagsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午5:51
 */
@Service
public class BookTagsServiceImpl implements BookTagsService {

    @Autowired
    private BookTagsDao bookTagsDao;

    @Autowired
    private BookTagsMapDao bookTagsMapDao;

    @Override
    public Integer save(BookTags bookTags) {
        return bookTagsDao.save(bookTags);
    }

    @Override
    public List<BookTags> queryByName(String name) {

        BookTagsConditions conditions = new BookTagsConditions();
        conditions.createCriteria().andNameLike(name);

        return bookTagsDao.selectByExample(conditions);
    }

    @Override
    public Integer saveTagMap(BookTagsMap bookTagsMap) {
        return bookTagsMapDao.save(bookTagsMap);
    }
}
