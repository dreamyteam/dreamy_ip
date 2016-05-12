package com.dreamy.dao.impl.sys;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.sys.SysOptionDao;
import com.dreamy.domain.sys.SysOption;
import com.dreamy.domain.sys.SysOptionConditions;
import com.dreamy.mapper.sys.SysOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/5/4.
 */
@Repository("sysOptionDao")
public class SysOptionDaoImpl extends BaseDaoImpl<SysOption,Integer,SysOptionConditions>implements SysOptionDao {
    @Resource
    private SysOptionMapper sysOptionMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(sysOptionMapper);
    }
}
