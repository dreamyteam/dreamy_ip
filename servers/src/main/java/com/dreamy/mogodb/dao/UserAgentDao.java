package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.UserAgent;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/22
 * Time: 下午6:18
 */
@Repository("UserAgentDao")
public class UserAgentDao extends MongoGenDao<UserAgent>{
    @Override
    protected Class<UserAgent> getEntityClass() {
        return UserAgent.class;
    }
}
