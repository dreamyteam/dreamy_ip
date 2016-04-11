package com.dreamy.service.iface.admin;

import com.dreamy.domain.admin.Role;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface RoleService {

    List<Role> getRoleList(Role role);

    Role getRoleById(int id);

    Role save(Role role);

    int update(Role role);


}
