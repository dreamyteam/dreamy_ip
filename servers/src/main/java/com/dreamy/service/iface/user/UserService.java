package com.dreamy.service.iface.user;

import com.dreamy.domain.user.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:14
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer save(User user);

    /**
     *
     * @param mobile
     * @return
     */
    User getUserByMobile(String mobile);

    /**
     *
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     *
     * @param user
     * @return
     */
    Integer updateByRecord(User user);

    /**
     *
     * @param response
     * @param user
     */
    Cookie rememerPwd(User user);
}
