package com.dreamy.service.admin;

import com.dreamy.domain.admin.RoleModel;

import java.util.List;
import java.util.Set;

/**
 * Created by wangyongxing on 16/4/11.
 */
public interface RoleModelService {

  /**
   *    获取角色对应的模块Id
   * @param roleId
   * @return
     */
  Set<Integer> getRoleModelList(Integer roleId);

  int deleteByRoleId(Integer roleId);

  RoleModel save(RoleModel roleModel);
}
