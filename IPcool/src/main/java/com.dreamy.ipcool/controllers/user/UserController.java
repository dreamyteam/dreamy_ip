package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.UserSession;
import com.dreamy.beans.params.ModifyPasswordParams;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserAttach;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.enums.QualificationEnums;
import com.dreamy.enums.SexEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ShortMessageService;
import com.dreamy.service.iface.VerificationCodeService;
import com.dreamy.service.iface.user.UserAttachService;
import com.dreamy.service.iface.user.UserService;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PasswordUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * user: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:37
 */
@RequestMapping("user")
@Controller
public class UserController extends IpcoolController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAttachService userAttachService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private ShortMessageService shortMessageService;

    @RequestMapping("/bio")
    public String bio(ModelMap map, HttpServletRequest request) {

        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            UserAttach userAttach = userAttachService.getByUserId(userSession.getUserId());

            map.put("user", userService.getUserById(userSession.getUserId()));
            map.put("userAttach", userAttach);
            map.put("sexEnums", SexEnums.values());
            map.put("qualifications", QualificationEnums.values());
            map.put("pageName", request.getParameter("pageName"));
            return "/user/bio";
        }

        return redirect("/");

    }

    @RequestMapping("/modify/password")
    public String modifyPassword(ModelMap map, HttpServletRequest request) {
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            map.put("wrongType", request.getParameter("wrongType"));
            map.put("pageName", request.getParameter("pageName"));
            User user = userService.getUserById(userSession.getUserId());
            if (user != null && user.getId() != null) {
                map.put("user", userService.getUserById(userSession.getUserId()));
                return "/user/password_modify";
            }
        }

        return redirect("/");
    }

    @RequestMapping("/modify/password/do")
    public String doModifyPassword(ModelMap map, HttpServletRequest request, ModifyPasswordParams passwordParams) {
        String returnUrlPre = "/user/modify/password?pageName=password";

        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            map.put("pageName", request.getParameter("pageName"));
            User user = userService.getUserById(userSession.getUserId());
            if (user != null && user.getId() != null) {
                Boolean res = PasswordUtils.isPasswordInvalid(user.getPassword(), passwordParams.getCurrentPassword());
                if (!res) {
                    return redirect(returnUrlPre + "&wrongType=1");
                } else {
                    if (passwordParams.getNewPassword().equals(passwordParams.getNewPasswordConfirm()) && StringUtils.isNotEmpty(passwordParams.getNewPassword())) {
                        user.setPassword(PasswordUtils.createPassword(passwordParams.getCurrentPassword()));
                        userService.save(user);

                        return redirect(returnUrlPre);
                    } else {
                        return redirect(returnUrlPre + "&wrongType=2");
                    }
                }
            }
        }

        return redirect("/");
    }

    @RequestMapping("/update")
    public String update(User user, UserAttach userAttach, HttpServletRequest request) {

        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            Integer userId = userSession.getUserId();
            User currentUser = userService.getUserById(userId);
            if (currentUser != null) {
                user.setId(userId);
                userService.updateByRecord(user);

                UserAttach userAtt = userAttachService.getByUserId(userId);
                if (userAtt.getId() != null) {
                    userAttach.setId(userAtt.getId());
                    userAttachService.updateByRecord(userAttach);
                } else {
                    userAttach.setUserId(userId);
                    userAttachService.save(userAttach);
                }
                return redirect("/user/bio?userKey=" + currentUser.getUserKey());
            }

        }
        return redirect("/");
    }

    @RequestMapping("/getpwd/doCheckPhone")
    public void checkPhoneCode(HttpServletRequest request, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        String mobile = (String) request.getAttribute("mobile");

        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        String errorMsg = "";

        //空值判断
        if (StringUtils.isEmpty(mobile)) {
            errorMsg = ("手机号码不能为空！");
        }

        Integer userId = 0;

        if (StringUtils.isEmpty(errorMsg)) {
            User user = userService.getUserByMobile(mobile);
            if(user.getId() == null) {
                errorMsg = ("手机号码不存在！");
            }else {
                userId = user.getId();
            }
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.check_phone;
            errorCodeEnums.setErrorMsg(errorMsg);
            bean.failure(errorCodeEnums);
        }else {
            bean.data("{userId:"+ userId +"}");
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }

    @RequestMapping("/getpwd/doPwd")
    public void doPwd(HttpServletRequest request, HttpServletResponse response, ModifyPasswordParams passwordParams) {
        InterfaceBean bean = new InterfaceBean().success();
        Integer userId = (Integer) request.getAttribute("userId");

        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        String errorMsg = "";
        if(userId == 0) {
            errorMsg = ("手机号码不能为空！");
        }
        User user = userService.getUserById(userId);
        if(user.getId() == null) {
            errorMsg = ("手机号码不存在！");
        }

        if (StringUtils.isEmpty(errorMsg)) {
            if (passwordParams.getNewPassword().equals(passwordParams.getNewPasswordConfirm()) && StringUtils.isNotEmpty(passwordParams.getNewPassword())) {
                user.setPassword(PasswordUtils.createPassword(passwordParams.getCurrentPassword()));
                userService.save(user);
            }else {
                errorMsg = ("两次密码不一致！");
            }
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.check_phone;
            errorCodeEnums.setErrorMsg(errorMsg);
            bean.failure(errorCodeEnums);
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");

    }

}
