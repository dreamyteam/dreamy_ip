package com.dreamy.admin.interceptor;

import com.dreamy.admin.controller.DashboardBaseController;
import com.dreamy.admin.controller.RootController;
import com.dreamy.admin.service.RoleNavService;
import com.dreamy.beans.UserSession;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.service.iface.admin.SysModelService;
import com.dreamy.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-6-30 上午10:14
 *
 * @Description:
 */


public class DashboardWebContentInterceptor extends WebContentInterceptor {

    @Autowired
    private RoleNavService roleNavService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        if (super.preHandle(request, response, arg)) {
            if (arg != null && arg instanceof DashboardBaseController) {
                DashboardBaseController controller = (DashboardBaseController) arg;
                if (controller.enableUserSession() && controller.checkLogin()) {
                    UserSession userSession = controller.getUserSession(request);
                    if (userSession == null || !userSession.isLogin()) {
                        response.sendRedirect("/login?service="
                                + HttpUtils.encodeUrl(HttpUtils.getFullUrl(request)));
                        return Boolean.FALSE;
                    }
                    Map<String, Map<String, String>> leftNavs = roleNavService.getLeftNavsByRoleId(1);
                    request.setAttribute("leftNavs",leftNavs);
                    request.setAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION, userSession);
                }

                return Boolean.TRUE;


            }

        }
        return Boolean.TRUE;
    }
}