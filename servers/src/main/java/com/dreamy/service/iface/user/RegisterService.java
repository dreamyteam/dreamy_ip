package com.dreamy.service.iface.user;

import com.dreamy.beans.params.RegisterParam;
import com.dreamy.enums.ErrorCodeEnums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午12:23
 */
public interface RegisterService {

    /**
     *
     * @param param
     * @return
     */
    ErrorCodeEnums checkRegisterParam(RegisterParam param);

    /**
     *
     * @param param
     * @return
     */
    String createUserKey(RegisterParam param);


}
