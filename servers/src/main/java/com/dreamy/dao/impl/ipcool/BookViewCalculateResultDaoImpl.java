package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookViewCalculateResultDao;
import com.dreamy.domain.ipcool.BookViewCalculateResult;
import com.dreamy.domain.ipcool.BookViewCalculateResultConditions;
import com.dreamy.mapper.ipcool.BookViewCalculateResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/21
 * Time: 下午5:10
 */
@Repository
public class BookViewCalculateResultDaoImpl extends BaseDaoImpl<BookViewCalculateResult, Integer, BookViewCalculateResultConditions> implements BookViewCalculateResultDao {

    @Autowired
    private BookViewCalculateResultMapper bookViewCalculateResultMapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(bookViewCalculateResultMapper);
    }
}
