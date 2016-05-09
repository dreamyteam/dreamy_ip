package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.UserSession;
import com.dreamy.beans.params.LoginParam;
import com.dreamy.domain.user.User;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.user.LoginService;
import com.dreamy.service.iface.user.UserService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午4:02
 */
@Controller
public class LoginController extends IpcoolController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/user/login")
    public void login(HttpServletResponse response, LoginParam param, HttpServletRequest request) {

        InterfaceBean bean = new InterfaceBean().success();

        ErrorCodeEnums errorCodeEnums = loginService.checkLoginParam(param);
        if (errorCodeEnums.getErrorCode() > 0) {
            bean.failure(errorCodeEnums);
        }

        User user = userService.getUserByMobile(param.getMobile());
        Boolean isPasswordValid = PasswordUtils.isPasswordInvalid(user.getPassword(), param.getPassword());
        if (isPasswordValid) {
            UserSession session = new UserSession();
            session.setUserId(user.getId());
            session.setUsername(user.getUserName());
            session.setUserKey(user.getUserKey());
            session.setImageUrl(user.getImageUrl());
            session.setInfo(user.getInfo());
            session.setSex(user.getSex());

            userSessionContainer.set(getUserSessionId(request), session);
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
