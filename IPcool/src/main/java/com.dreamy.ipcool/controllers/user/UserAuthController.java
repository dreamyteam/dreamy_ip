package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.UserSession;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserPart;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.enums.UserPartEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.user.UserAuthService;
import com.dreamy.service.iface.user.UserPartService;
import com.dreamy.service.iface.user.UserService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by mac on 16/6/13.
 */
@RequestMapping("user")
@Controller
public class UserAuthController extends IpcoolController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserPartService userPartService;
    @Autowired
    private UserService userService;

    @RequestMapping("/auth")
    public String auth(ModelMap map, HttpServletRequest request)  {
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {

            UserAuth userAuth = userAuthService.getUserAuthByUserId(userSession.getUserId());

            map.put("user", userService.getUserById(userSession.getUserId()));
            map.put("userAuth", userAuth);
            map.put("wrongType", request.getParameter("wrongType"));
            map.put("pageName", request.getParameter("pageName"));
            return "/user/auth";
        }

        return redirect("/");
    }

    @RequestMapping("/authPersonal")
    public String authPersonal(ModelMap map, HttpServletRequest request) {
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            List<UserPart> personalPart = userPartService.getUserPartByType(UserPartEnums.type_personal.getValue());
            map.put("personalPart", personalPart);
            map.put("user", userService.getUserById(userSession.getUserId()));
            map.put("pageName", request.getParameter("pageName"));
            return "/user/auth_person";
        }
        return redirect("/");
    }

    @RequestMapping("/authBusiness")
    public String authBusiness(ModelMap map, HttpServletRequest request) {
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            List<UserPart> businessPart = userPartService.getUserPartByType(UserPartEnums.type_business.getValue());
            map.put("businessPart", businessPart);
            map.put("user", userService.getUserById(userSession.getUserId()));
            map.put("pageName", request.getParameter("pageName"));
            return "/user/auth_business";
        }
        return redirect("/");
    }

    @RequestMapping("/authApply")
    public void authApply(HttpServletRequest request, HttpServletResponse response, UserAuth userAuth) {
        InterfaceBean bean = new InterfaceBean().success();
        String errorMsg = "";
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        UserSession userSession = getUserSession(request);

        if (userSession != null && userSession.getUserId() > 0) {
            userAuth.setUserId(userSession.getUserId());
            userAuthService.doAuthApply(userAuth);
        }else {
            errorMsg = "您未登录,不能进行此操作!";
        }

        if(StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.auth_apply_failed;
            errorCodeEnums.setErrorMsg(errorMsg);
            bean.failure(errorCodeEnums);
        }
        interfaceReturn(response, JsonUtils.toString(bean), "");
    }

    @RequestMapping("/businessAuthCheckCode")
    public String businessAuthCheckCode(ModelMap map, HttpServletRequest request) {
        String returnUrlPre = "/user/auth?pageName=auth";

        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            map.put("pageName", request.getParameter("pageName"));
            String valideCode  = request.getParameter("valideCode");
            ErrorCodeEnums errorCodeEnums = userAuthService.doBusinessAuthCheckCode(userSession.getUserId(), valideCode);
            if(errorCodeEnums.getErrorCode() > 0) {
                return redirect(returnUrlPre + "&wrongType=1");
            }else {
                return redirect(returnUrlPre);
            }
        }
        return redirect("/");
    }

}
