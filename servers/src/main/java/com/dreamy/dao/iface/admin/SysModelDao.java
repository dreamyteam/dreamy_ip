package com.dreamy.dao.iface.admin;

import com.dreamy.dao.BaseDao;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.domain.admin.SysModelConditions;

import java.util.List;

/**
 * Created by wangyongxing on 16/3/31.
 */
public interface SysModelDao extends BaseDao<SysModel,Integer,SysModelConditions> {

    public List<SysModel> selectByRoles(List<Integer> roles);

    public List<SysModel> getByUserId(Integer userId);




}
