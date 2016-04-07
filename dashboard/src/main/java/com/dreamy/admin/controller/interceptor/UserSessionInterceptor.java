package com.dreamy.admin.controller.interceptor;

import com.dreamy.admin.controller.RootController;
import com.dreamy.beans.UserSession;
import com.dreamy.service.cache.DefaultCacheManager;
import com.dreamy.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by wangyongxing on 16/4/1.
 */
public class UserSessionInterceptor  extends DefaultCacheManager implements HandlerInterceptor {

    private CookieHandler cookieHandler;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof RootController) {
            RootController controller = (RootController) handler;
            if (controller.enableUserSession() && controller.getUserSessionId(request) == null) {
                String id = cookieHandler.getCookieValue(request);
                if (StringUtils.isNotEmpty(id)) {
                    UserSession userSession =(UserSession) getCacheService().get(id);
                    if (userSession != null) {
                        request.setAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION, userSession);
                    }
                } else {
                    id = UUID.randomUUID().toString();
                    cookieHandler.addCookie(response, id, 30 * 24 * 3600);
                }
                request.setAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION_ID, id);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (handler instanceof RootController) {
            RootController controller = (RootController) handler;
            if (controller.enableUserSession()) {
                String id = controller.getUserSessionId(request);
                if (StringUtils.isNotEmpty(id)) {
                    UserSession userSession = (UserSession) request.getAttribute(RootController.REQUEST_ATTRIBUTE_USERSESSION);
                    if (userSession != null) {
                        getCacheService().put(id, userSession);// keep alive
                    }
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    public CookieHandler getCookieHandler() {
        return cookieHandler;
    }

    public void setCookieHandler(CookieHandler cookieHandler) {
        this.cookieHandler = cookieHandler;
    }



}
