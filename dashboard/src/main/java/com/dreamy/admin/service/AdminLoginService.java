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
@Service
public class AdminLoginService {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    @Qualifier("userSessionContainer")
    private UserSessionContainer<UserSession> userSessionContainer;

    public InterfaceBean doLogin(LoginParam loginParam) {
        InterfaceBean bean = new InterfaceBean().success();
        if (loginParam != null) {
            if (StringUtils.isNotEmpty(loginParam.getUserName())) {
                if (StringUtils.isNotEmpty(loginParam.getPassword())) {
                    AdminUser adminUser = adminUserService.getByUsername(loginParam.getUserName());
                    if (adminUser != null) {

                        if (HashUtils.md5(loginParam.getPassword()).equals(adminUser.getPassword())) {
                            //增加登录信息
                            addRedisSession(loginParam.getSessionId(), adminUser);
                            bean.setData(adminUser);
                        } else {
                            bean.failure(Constants.InterfacebBeanCode.FAILURE, "用户密码不正确!");
                        }
                    } else {
                        bean.failure(Constants.InterfacebBeanCode.FAILURE, "用户不存在!");
                    }
                } else {
                    bean.failure(Constants.InterfacebBeanCode.FAILURE, "用户密码不能为空!");
                }
            }
        } else {
            bean.failure(Constants.InterfacebBeanCode.FAILURE, "系统错误!");
        }

        return bean;
    }


    private void addRedisSession(String sessionId, AdminUser adminUser) {
        if (adminUser != null && StringUtils.isNotEmpty(sessionId)) {
            UserSession session = new UserSession();
            session.setUserId(adminUser.getId());
            session.setUsername(adminUser.getUsername());
            if (session != null) {
                userSessionContainer.set(sessionId, session);
            }
        }
    }
}
