package com.dreamy.admin.service;

import com.dreamy.admin.beans.Constants;
import com.dreamy.admin.beans.LoginParam;
import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.UserSession;
import com.dreamy.beans.UserSessionContainer;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.service.iface.admin.AdminUserService;
import com.dreamy.utils.HashUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface AdminLoginService {

    /**
     * 用户登录
     *
     * @param loginParam
     * @return
     */
    public InterfaceBean doLogin(LoginParam loginParam);
}
