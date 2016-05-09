package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.params.RegisterParam;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.impl.user.RegisterServiceImpl;
import com.dreamy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:37
 */
@RequestMapping("user")
@Controller
public class UserController extends IpcoolController {

    @Autowired
    private RegisterServiceImpl registerService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public void register(RegisterParam param, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        ErrorCodeEnums errorCodeEnums = registerService.checkRegisterParam(param);
        if (errorCodeEnums.getErrorCode() > 0) {
            bean.failure(errorCodeEnums);
        } else {
            
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }

    @RequestMapping("/account")
    public String account() {
        return "/user/account";
    }

    @RequestMapping("/bio")
    public String bio() {
        return "/user/bio";
    }

    @RequestMapping("/following")
    public String followList() {
        return "/user/following";
    }

    @RequestMapping("/view/history")
    public String history() {
        return "/user/history";
    }

    @RequestMapping("/modify/password")
    public String modifyPassword() {
        return "/user/password_modify";
    }
}
