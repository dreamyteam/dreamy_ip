package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.user.UserAuthDao;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserAuthConditions;
import com.dreamy.mapper.user.UserAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by mac on 16/6/14.
 */
@Repository
public class UserAuthDaoImpl extends BaseDaoImpl<UserAuth, Integer, UserAuthConditions> implements UserAuthDao {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(userAuthMapper);
    }
}
