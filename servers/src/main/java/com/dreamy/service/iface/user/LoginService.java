package com.dreamy.service.iface.user;

import com.dreamy.beans.params.LoginParam;
import com.dreamy.enums.ErrorCodeEnums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:14
 */
public interface LoginService {

    /**
     *
     * @param param
     * @return
     */
    ErrorCodeEnums checkLoginParam(LoginParam param);

}
