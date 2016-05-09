package com.dreamy.service.iface.user;

import com.dreamy.beans.params.RegisterParam;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:14
 */
public interface LoginService {

    /**
     * 检测登陆信息
     *
     * @param param
     * @return
     */
    Boolean isLoginParamsOk(RegisterParam param);

}
