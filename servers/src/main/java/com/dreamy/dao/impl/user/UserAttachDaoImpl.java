package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.User.UserAttachDao;
import com.dreamy.domain.user.UserAttach;
import com.dreamy.domain.user.UserAttachConditions;
import com.dreamy.mapper.user.UserAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午8:42
 */
@Repository
public class UserAttachDaoImpl extends BaseDaoImpl<UserAttach,Integer,UserAttachConditions> implements UserAttachDao {

    @Autowired
    private UserAttachMapper mapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
