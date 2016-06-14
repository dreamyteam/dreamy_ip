package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.user.UserPartDao;
import com.dreamy.domain.user.UserPart;
import com.dreamy.domain.user.UserPartConditions;
import com.dreamy.mapper.user.UserPartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by mac on 16/6/14.
 */
@Repository
public class UserPartDaoImpl extends BaseDaoImpl<UserPart, Integer, UserPartConditions> implements UserPartDao {

    @Autowired
    private UserPartMapper userPartMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(userPartMapper);
    }
}
