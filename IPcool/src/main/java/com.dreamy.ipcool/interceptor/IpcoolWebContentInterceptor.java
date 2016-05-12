package com.dreamy.ipcool.interceptor;

import com.dreamy.beans.UserSession;
import com.dreamy.ipcool.controllers.IpcoolBaseController;
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


public class IpcoolWebContentInterceptor extends WebContentInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        if (super.preHandle(request, response, arg)) {
            if (arg != null && arg instanceof IpcoolBaseController) {
                IpcoolBaseController controller = (IpcoolBaseController) arg;
                if (controller.enableUserSession() && controller.checkLogin()) {
                    UserSession userSession = controller.getUserSession(request);
                    if (userSession == null || !userSession.isLogin()) {
                        response.sendRedirect("/login?service="
                                + HttpUtils.encodeUrl(HttpUtils.getFullUrl(request)));
                        return Boolean.FALSE;
                    }

                    String currentUrl = request.getRequestURI();
                }

                return Boolean.TRUE;


            }

        }
        return Boolean.TRUE;
    }
}