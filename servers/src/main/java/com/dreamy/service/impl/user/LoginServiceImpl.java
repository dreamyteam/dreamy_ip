package com.dreamy.service.impl.user;

import com.dreamy.beans.params.RegisterParam;
import com.dreamy.service.iface.user.LoginService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:14
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Boolean isLoginParamsOk(RegisterParam param) {
        return null;
    }
}
