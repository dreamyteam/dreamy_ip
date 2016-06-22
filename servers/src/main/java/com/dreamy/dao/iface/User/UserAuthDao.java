package com.dreamy.dao.iface.user;

import com.dreamy.dao.BaseDao;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserAuthConditions;

/**
 * Created by mac on 16/6/14.
 */
public interface UserAuthDao extends BaseDao<UserAuth, Integer, UserAuthConditions> {

    UserAuth getUserAuthByUserId(Integer userId);

}
