package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookIndexHistoryDao;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookIndexHistoryConditions;
import com.dreamy.mapper.ipcool.BookIndexHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Repository("bookIndexHistoryDao")
public class BookIndexHistoryDaoImpl extends BaseDaoImpl<BookIndexHistory,Integer,BookIndexHistoryConditions> implements BookIndexHistoryDao {
    @Resource
    private BookIndexHistoryMapper bookIndexHistoryMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
       super.setBaseMapper(bookIndexHistoryMapper);
    }
}
