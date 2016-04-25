package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.UserAgents;

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
    UserAgents getById(Integer id);

    /**
     *
     * @return
     */
    UserAgents getOneByRandom();
}
