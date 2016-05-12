package com.dreamy.admin.service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/20
 * Time: 上午11:34
 */
public interface RoleNavService {
    /**
     * @param roleId
     * @return
     */
    Map<String,Map<String, String>> getLeftNavsByRoleId(Integer roleId);
}
