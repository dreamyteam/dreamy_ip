package com.dreamy.dao.impl.admin;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.admin.UserRoleDao;
import com.dreamy.domain.admin.UserRole;
import com.dreamy.domain.admin.UserRoleConditions;
import com.dreamy.mapper.admin.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/1.
 */
@SuppressWarnings("unchecked")
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole,Integer,UserRoleConditions> implements UserRoleDao {
    @Resource
    private UserRoleMapper userRoleMapper;


    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(userRoleMapper);
    }
}
