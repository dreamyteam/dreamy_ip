package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.UserSession;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserPart;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.user.UserAuthService;
import com.dreamy.service.iface.user.UserPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/auth")
    public String auth(ModelMap map, HttpServletRequest request)  {
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {

            UserAuth userAuth = userAuthService.getUserAuthByUserId(userSession.getUserId());

            List<UserPart> personalPart = userPartService.getUserPartByType(1);

            List<UserPart> businessPart = userPartService.getUserPartByType(2);

            map.put("personalPart", personalPart);
            map.put("businessPart", businessPart);
            map.put("userAuth", userAuth);
            map.put("pageName", request.getParameter("pageName"));
            return "/user/auth";
        }

        return redirect("/");
    }

}
