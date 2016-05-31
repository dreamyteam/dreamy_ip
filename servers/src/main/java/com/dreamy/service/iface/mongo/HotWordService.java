package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.HotWord;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 下午12:06
 */
public interface HotWordService {

    /**
     *
     * @param id
     * @return
     */
    HotWord getById(Integer id);
}
