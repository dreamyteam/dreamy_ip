package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.BookIndexTaskLogDao;
import com.dreamy.domain.ipcool.BookIndexTaskLog;
import com.dreamy.domain.ipcool.BookIndexTaskLogConditions;
import com.dreamy.mapper.ipcool.BookIndexTaskLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Repository("BookIndexTaskLogDao")
public class BookIndexTaskLogDaoImpl extends BaseDaoImpl<BookIndexTaskLog,Integer,BookIndexTaskLogConditions> implements BookIndexTaskLogDao {
    @Resource
    private BookIndexTaskLogMapper mapper;

    @Override
    @Autowired
    public void setBaseMapper() {
       super.setBaseMapper(mapper);
    }
}
