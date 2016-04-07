package com.dreamy.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wangyongxing on 16/4/1.
 */
public final class CookieUtils {

    /**
     * 获取cookie中对应的值
     *
     * @param request request请求对象
     * @param name    cookie中的key
     * @return cookie对应值
     */
    public static String getValue(HttpServletRequest request, String name) {
        Cookie cookie = get(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return ConstStrings.EMPTY;
    }

    /**
     * 通过遍历request中带过来的cookie获取到与key匹配的cookie值
     *
     * @param request request请求对象
     * @param name    cookie中的key
     * @return
     */
    public static Cookie get(HttpServletRequest request, String name) {
        if (name != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie != null && name.equals(cookie.getName())) {
                        return cookie;
                    }
                }
            }
        }

        return null;
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie 名称
     * @param value    cookie 值
     */
    public static void add(HttpServletResponse response, String name, String value) {
        add(response, name, value, null, null, false, -1);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie 名称
     * @param value    cookie 值
     * @param age      有效时间
     */
    public static void add(HttpServletResponse response, String name, String value, int age) {
        add(response, name, value, null, null, false, age);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie 名称
     * @param value    cookie 值
     * @param domain   主域名
     * @param age      有效时间
     */
    public static void add(HttpServletResponse response, String name, String value, String domain, int age) {
        add(response, name, value, domain, null, false, age);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie 名称
     * @param value    cookie 值
     * @param domain   主域名
     * @param path     路径
     * @param httpOnly 是否仅http
     * @param age      有效时间
     */
    public static void add(HttpServletResponse response, String name, String value, String domain, String path,
                           boolean httpOnly, int age) {
        if (StringUtils.isNotEmpty(name)) {
            StringBuilder buff = new StringBuilder(128);
            buff.append(name);
            buff.append("=");
            buff.append(StringUtils.nullToEmpty(value));
            buff.append(ConstStrings.SEMICOLON);

            buff.append("Path=");
            buff.append(StringUtils.emptyToDefault(path, ConstStrings.SLASH));
            buff.append(ConstStrings.SEMICOLON);

            if (domain != null) {
                buff.append("Domain=");
                buff.append(domain);
                buff.append(ConstStrings.SEMICOLON);
            }

            if (age >= 0) {
                buff.append("Expires=");
                buff.append(WebUtils.toString(new Date(System.currentTimeMillis() + age * 1000l)));
                buff.append(ConstStrings.SEMICOLON);
            }

            if (httpOnly) {
                buff.append("HTTPOnly");
            }

            response.addHeader("Set-Cookie", buff.toString());
        }
    }
}
