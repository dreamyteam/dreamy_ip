package com.dreamy.service.impl.user;

import com.dreamy.dao.iface.user.UserAttachDao;
import com.dreamy.domain.user.UserAttach;
import com.dreamy.domain.user.UserAttachConditions;
import com.dreamy.service.iface.user.UserAttachService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午8:38
 */
@Service
public class UserAttachServiceImpl implements UserAttachService {

    @Autowired
    private UserAttachDao userAttachDao;

    @Override
    public Integer save(UserAttach userAttach) {

        return userAttachDao.save(userAttach);
    }

    @Override
    public UserAttach getByUserId(Integer userId) {
        UserAttach userAttach = new UserAttach();
        UserAttachConditions conditions = new UserAttachConditions();
        conditions.createCriteria().andUserIdEqualTo(userId);

        List<UserAttach> userAttachList = userAttachDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(userAttachList)) {
            userAttach = userAttachList.get(0);
        }

        return userAttach;
    }

    @Override
    public Integer updateByRecord(UserAttach userAttach) {
        return userAttachDao.update(userAttach);
    }
}
