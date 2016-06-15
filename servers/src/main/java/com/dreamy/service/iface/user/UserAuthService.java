package com.dreamy.service.iface.user;

import com.dreamy.domain.user.UserAuth;
import com.dreamy.enums.ErrorCodeEnums;

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

    /**
     * 处理认证申请
     * @param userAuth
     */
    void doAuthApply(UserAuth userAuth);

    /**
     * 校验企业认证验证码
     * @param userId
     * @param valideCode
     * @return
     */
    ErrorCodeEnums doBusinessAuthCheckCode(Integer userId, String valideCode);
}
