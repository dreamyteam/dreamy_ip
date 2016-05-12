package com.dreamy.dao.impl.user;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.user.UserApplyDao;
import com.dreamy.domain.user.UserApply;
import com.dreamy.domain.user.UserApplyConditions;
import com.dreamy.mapper.user.UserApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 上午10:23
 */
@Repository
public class UserApplyDaoImpl extends BaseDaoImpl<UserApply, Integer, UserApplyConditions> implements UserApplyDao {

    @Autowired
    private UserApplyMapper mapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(mapper);
    }
}
