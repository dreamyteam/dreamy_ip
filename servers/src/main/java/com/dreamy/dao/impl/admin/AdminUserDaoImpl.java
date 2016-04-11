package com.dreamy.dao.impl.admin;

import com.dreamy.dao.admin.AdminUserDao;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.domain.admin.AdminUserConditions;
import com.dreamy.mapper.admin.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/8.
 */
@Repository("adminUserDao")
public class AdminUserDaoImpl extends BaseDaoImpl<AdminUser,Integer,AdminUserConditions> implements AdminUserDao {
    @Resource
    private AdminUserMapper adminUserMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(adminUserMapper);
    }
}
