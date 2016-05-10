package com.dreamy.service.iface.user;

import com.dreamy.domain.user.UserAttach;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:14
 */
public interface UserAttachService {
    /**
     * @param userAttach
     * @return
     */
    Integer save(UserAttach userAttach);

    /**
     *
     * @param userId
     * @return
     */
    UserAttach getByUserId(Integer userId);

    /**
     *
     * @param userAttach
     * @return
     */
    Integer updateByRecord(UserAttach userAttach);
}
