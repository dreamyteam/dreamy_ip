package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.UserAgent;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/22
 * Time: 下午6:42
 */
public interface UserAgentService {
    /**
     * @param id
     * @return
     */
    UserAgent getById(Integer id);

    /**
     *
     * @return
     */
    UserAgent getOneByRandom();
}
