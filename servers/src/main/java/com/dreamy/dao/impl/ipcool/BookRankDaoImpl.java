package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookRankDao;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankConditions;
import com.dreamy.mapper.ipcool.BookRankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Repository("bookRankDao")
public class BookRankDaoImpl extends BaseDaoImpl<BookRank,Integer,BookRankConditions> implements BookRankDao {

    @Resource
    private BookRankMapper bookRankMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
            super.setBaseMapper(bookRankMapper);

    }
}
