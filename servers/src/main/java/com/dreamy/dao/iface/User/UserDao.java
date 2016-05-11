package com.dreamy.dao.iface.user;

import com.dreamy.dao.BaseDao;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserConditions;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午1:33
 */
public interface UserDao extends BaseDao<User,Integer,UserConditions> {
}
