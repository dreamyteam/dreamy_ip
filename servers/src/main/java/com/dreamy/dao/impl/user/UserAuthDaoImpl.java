package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.user.UserAuthDao;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserAuthConditions;
import com.dreamy.mapper.user.UserAuthMapper;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public UserAuth getUserAuthByUserId(Integer userId) {
        UserAuth userAuth = new UserAuth();
        UserAuthConditions conditions = new UserAuthConditions();
        conditions.createCriteria().andUserIdEqualTo(userId);
        List<UserAuth> list = selectByExample(conditions);
        if(CollectionUtils.isNotEmpty(list)) {
            userAuth = list.get(0);
        }
        return userAuth;
    }
}
