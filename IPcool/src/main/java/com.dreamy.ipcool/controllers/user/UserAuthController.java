package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.UserSession;
import com.dreamy.ipcool.controllers.IpcoolController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mac on 16/6/13.
 */
@RequestMapping("user")
@Controller
public class UserAuthController extends IpcoolController {

    @RequestMapping("/auth")
    public String auth(ModelMap map, HttpServletRequest request)  {
        UserSession userSession = getUserSession(request);
        if (userSession != null && userSession.getUserId() > 0) {
            map.put("pageName", request.getParameter("pageName"));
            return "/user/auth";
        }

        return redirect("/");
    }

}
