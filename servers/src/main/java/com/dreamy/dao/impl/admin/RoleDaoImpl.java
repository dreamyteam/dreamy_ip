package com.dreamy.dao.impl.admin;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.domain.admin.Role;
import com.dreamy.domain.admin.RoleConditions;
import com.dreamy.mapper.admin.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dreamy.dao.iface.admin.RoleDao;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role,Integer,RoleConditions> implements RoleDao {
    @Resource
    private RoleMapper roleMapper;


    //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
    @Autowired
    public void setBaseMapper(){

        super.setBaseMapper(roleMapper);
    }



}
