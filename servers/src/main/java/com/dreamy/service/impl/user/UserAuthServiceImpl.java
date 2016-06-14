package com.dreamy.service.impl.user;

import com.dreamy.dao.iface.user.UserAuthDao;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserAuthConditions;
import com.dreamy.service.iface.user.UserAuthService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mac on 16/6/14.
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public UserAuth getUserAuthByUserId(Integer userId) {
        UserAuth userAuth = new UserAuth();
        UserAuthConditions conditions = new UserAuthConditions();
        conditions.createCriteria().andUserIdEqualTo(userId);
        List<UserAuth> list = userAuthDao.selectByExample(conditions);
        if(CollectionUtils.isNotEmpty(list)) {
            userAuth = list.get(0);
        }
        return userAuth;
    }
}