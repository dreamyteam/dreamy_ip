package com.dreamy.admin.interceptor;

import com.dreamy.admin.controller.DashboardBaseController;
import com.dreamy.admin.controller.RootController;
import com.dreamy.beans.UserSession;
import com.dreamy.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-6-30 上午10:14
 *
 * @Description:
 */


public class DashboardWebContentInterceptor extends WebContentInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        if (super.preHandle(request, response, arg)) {
            if (arg != null && arg instanceof DashboardBaseController) {
                DashboardBaseController controller = (DashboardBaseController) arg;
                System.out.println(controller.enableUserSession()+"wqwqwq");
                System.out.println(controller.checkLogin()+"wyyy");
                if (controller.enableUserSession() && controller.checkLogin()) {
                    UserSession userSession = controller.getUserSession(request);
                    if (userSession == null || !userSession.isLogin()) {
                        response.sendRedirect("/admin/login?service="
                                + HttpUtils.encodeUrl(HttpUtils.getFullUrl(request)));
                        return Boolean.FALSE;
                    }
                    request.setAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION, userSession);
                }

                return Boolean.TRUE;


            }

        }
        return Boolean.TRUE;
    }
}