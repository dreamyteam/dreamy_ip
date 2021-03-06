package com.dreamy.dao.impl.admin;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.admin.RoleModelDao;
import com.dreamy.domain.admin.RoleModel;
import com.dreamy.domain.admin.RoleModelConditions;
import com.dreamy.mapper.admin.RoleModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/1.
 */
@SuppressWarnings("unchecked")
@Repository("roleModelDao")
public class RoleModelDaoImpl extends BaseDaoImpl<RoleModel,Integer,RoleModelConditions> implements RoleModelDao {

    @Resource
    private RoleModelMapper roleModelMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(roleModelMapper);
    }


}
