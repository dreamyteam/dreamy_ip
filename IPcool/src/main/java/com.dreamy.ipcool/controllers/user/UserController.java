package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.UserSession;
import com.dreamy.beans.params.ModifyPasswordParams;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserAttach;
import com.dreamy.enums.QualificationEnums;
import com.dreamy.enums.SexEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.user.UserAttachService;
import com.dreamy.service.iface.user.UserService;
import com.dreamy.utils.PasswordUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    private UserService userService;

    @Autowired
    private UserAttachService userAttachService;

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
        return null;

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

        return null;
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

        return null;
    }

    @RequestMapping("/logout")
    public String logout() {
        return "/homepage/homepage";
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
        return null;
    }
}
