package com.dreamy.admin.controller;

import com.dreamy.admin.beans.Constants;
import com.dreamy.admin.beans.LoginParam;
import com.dreamy.admin.service.AdminLoginService;
import com.dreamy.beans.InterfaceBean;
import com.dreamy.utils.ConstStrings;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Controller
@RequestMapping(value = {"/admin/login", "/"})
public class LoginController extends DashboardController {

    @Resource
    private AdminLoginService adminLoginService;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {

        return "/admin/main/login";
    }


    @RequestMapping(value = "/admin/loginPost",method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, ModelMap model,
                            @RequestParam(value = "userName", defaultValue = ConstStrings.EMPTY) String userName,
                            @RequestParam(value = "password", defaultValue = ConstStrings.EMPTY) String password,
                            @RequestParam(value = "service", required = false, defaultValue = ConstStrings.EMPTY) String service) {
        if (StringUtils.isNotEmpty(userName)) {
            if (StringUtils.isNotEmpty(password)) {
                InterfaceBean bean = adminLoginService.doLogin(LoginParam.getNewInstance(userName, password, WebUtils.getRemoteAddress(request), getUserSessionId(request)));
                if (bean.getErrorCode() == Constants.InterfacebBeanCode.SUCCESS) {
                    //重定向到指定页面
                    return redirect("/admin/index", service);
                } else {
                    model.put("error", bean.getErrorMsg());
                }
            } else {
                model.put("error", "密码不能为空");
            }
        } else {
            model.put("error", "手机号不能为空");
        }

        model.put("userName", userName);
        model.put("password", password);
        model.put("service", service);

        return "/admin/main/login";
    }


}
