package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookTagsMapDao;
import com.dreamy.domain.ipcool.BookTagsMap;
import com.dreamy.domain.ipcool.BookTagsMapConditions;
import com.dreamy.mapper.ipcool.BookTagsMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午7:43
 */
@Repository
public class BookTagsMapDaoImpl extends BaseDaoImpl<BookTagsMap, Integer, BookTagsMapConditions> implements BookTagsMapDao {

    @Autowired
    private BookTagsMapMapper bookTagsMapMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(bookTagsMapMapper);
    }
}
