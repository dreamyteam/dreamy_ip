package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookViewDao;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.BookViewConditions;
import com.dreamy.mapper.ipcool.BookViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Repository("bookViewDao")
public class BookViewDaoImpl extends BaseDaoImpl<BookView,Integer,BookViewConditions> implements BookViewDao {

    @Resource
    private BookViewMapper bookViewMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(bookViewMapper);
    }
}
