package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookTagsDao;
import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsConditions;
import com.dreamy.mapper.ipcool.BookTagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午5:47
 */
@Repository
public class BookTagsDaoImpl extends BaseDaoImpl<BookTags, Integer, BookTagsConditions> implements BookTagsDao {

    @Autowired
    private BookTagsMapper bookTagsMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(bookTagsMapper);
    }
}
