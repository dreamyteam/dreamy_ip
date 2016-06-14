package com.dreamy.service.iface.user;

import com.dreamy.domain.user.UserPart;

import java.util.List;

/**
 * Created by mac on 16/6/14.
 */
public interface UserPartService {

    /**
     * 根据类型获取角色列表
     * @param type
     * @return
     */
    List<UserPart> getUserPartByType(Integer type);
}
