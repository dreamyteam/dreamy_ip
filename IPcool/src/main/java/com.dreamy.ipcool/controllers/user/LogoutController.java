package com.dreamy.ipcool.controllers.user;

import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.utils.ConstStrings;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 上午10:11
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController extends IpcoolController {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request,
                         @RequestParam(value = "service", required = false, defaultValue = ConstStrings.EMPTY) String service) {
        String sid = getUserSessionId(request);
        if (StringUtils.isNotEmpty(sid)) {
            clearUserSession(request);
            userSessionContainer.clear(sid);
        }
        return redirect("/");
    }
}
