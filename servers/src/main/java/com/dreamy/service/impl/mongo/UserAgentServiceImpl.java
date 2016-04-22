package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.UserAgent;
import com.dreamy.mogodb.dao.UserAgentDao;
import com.dreamy.service.iface.mongo.UserAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/22
 * Time: 下午6:43
 */
@Service
public class UserAgentServiceImpl implements UserAgentService {

    @Autowired
    private UserAgentDao userAgentDao;

    @Override
    public UserAgent getById(Integer id) {
        return userAgentDao.queryById(id);
    }

    @Override
    public UserAgent getOneByRandom() {
        Random random = new Random();
        Integer id = 2 + random.nextInt(999);
        return getById(id);
    }
}
