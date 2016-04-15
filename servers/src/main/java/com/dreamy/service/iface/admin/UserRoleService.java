package com.dreamy.service.iface.admin;

import com.dreamy.domain.admin.UserRole;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface UserRoleService {

    /** 根据用户id查询角色
    * @param userId
    * @return
            */
    public List<UserRole> getUserToRoleList(Integer userId);

    public void save(UserRole userRole);

    public int updateRoleId(UserRole userRole);
}
