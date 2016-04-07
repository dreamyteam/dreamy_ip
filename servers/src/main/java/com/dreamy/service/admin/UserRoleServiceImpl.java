package com.dreamy.service.admin;

import com.dreamy.dao.admin.UserRoleDao;
import com.dreamy.domain.admin.Role;
import com.dreamy.domain.admin.RoleConditions;
import com.dreamy.domain.admin.UserRole;
import com.dreamy.domain.admin.UserRoleConditions;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Override
    public List<UserRole> getUserToRoleList(Integer userId) {
        UserRoleConditions roleConditions=new UserRoleConditions();
        roleConditions.createCriteria().andAdminIdEqualTo(userId);
        return userRoleDao.selectByExample(roleConditions);
    }
}
