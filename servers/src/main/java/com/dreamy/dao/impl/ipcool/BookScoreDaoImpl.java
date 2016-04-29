package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookScoreDao;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookScoreConditions;
import com.dreamy.mapper.ipcool.BookScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Repository("bookScoreDao")
public class BookScoreDaoImpl extends BaseDaoImpl<BookScore,Integer,BookScoreConditions> implements BookScoreDao  {
    @Resource
    BookScoreMapper bookScoreMapper;


    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(bookScoreMapper);
    }
}
