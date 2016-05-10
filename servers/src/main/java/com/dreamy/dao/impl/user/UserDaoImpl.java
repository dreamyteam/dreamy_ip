package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.User.UserDao;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserConditions;
import com.dreamy.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
* Created with IntelliJ IDEA.
* User: yujianfu (yujianfu@duotin.com)
* Date: 16/5/9
* Time: 下午1:34
*/
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer, UserConditions> implements UserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(userMapper);
    }
}
