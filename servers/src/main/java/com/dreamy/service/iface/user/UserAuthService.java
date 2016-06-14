package com.dreamy.service.iface.user;

import com.dreamy.domain.user.UserAuth;

/**
 * Created by mac on 16/6/14.
 */
public interface UserAuthService {

    /**
     * 根据用户id查询用户认证信息
     * @param userId
     * @return
     */
    UserAuth getUserAuthByUserId(Integer userId);

}
