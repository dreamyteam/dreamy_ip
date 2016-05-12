package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookRankHistoryDao;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookRankHistoryConditions;
import com.dreamy.mapper.ipcool.BookRankHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Repository("bookRankHistoryDao")
public class BookRankHistoryDaoImpl extends BaseDaoImpl<BookRankHistory,Integer,BookRankHistoryConditions> implements BookRankHistoryDao {

    @Resource
    private BookRankHistoryMapper bookRankHistoryMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(bookRankHistoryMapper);

    }
}
