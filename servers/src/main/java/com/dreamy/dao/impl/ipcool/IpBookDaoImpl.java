package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.IpBookDao;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.domain.ipcool.IpBookConditions;
import com.dreamy.mapper.ipcool.IpBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Repository("IpBookDao")
public class IpBookDaoImpl  extends BaseDaoImpl<IpBook,Integer,IpBookConditions>implements IpBookDao {

    @Resource
    private IpBookMapper ipBookMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(ipBookMapper);
    }
}
