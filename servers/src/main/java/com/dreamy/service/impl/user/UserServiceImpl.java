package com.dreamy.service.impl.user;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.User.UserDao;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserConditions;
import com.dreamy.service.iface.user.UserService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserByMobile(String mobile) {
        User user = new User();
        UserConditions conditions = new UserConditions();
        conditions.createCriteria().andPhoneEqualTo(mobile);

        Page page = new Page();
        page.setPageSize(1);
        conditions.setPage(page);

        List<User> users = userDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(users)) {
            user = users.get(0);
        }

        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.selectById(userId);
    }

    @Override
    public Integer updateByRecord(User user) {
        return userDao.update(user);
    }
}
