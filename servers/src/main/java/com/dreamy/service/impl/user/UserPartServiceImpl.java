package com.dreamy.service.impl.user;

import com.dreamy.dao.iface.user.UserPartDao;
import com.dreamy.domain.user.UserPart;
import com.dreamy.service.iface.user.UserPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mac on 16/6/14.
 */
@Service
public class UserPartServiceImpl implements UserPartService {

    @Autowired
    private UserPartDao userPartDao;

    @Override
    public List<UserPart> getUserPartByType(Integer type) {
        return null;
    }
}
