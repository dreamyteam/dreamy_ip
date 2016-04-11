package com.dreamy.service.admin;

import com.dreamy.dao.admin.AdminUserDao;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.domain.admin.AdminUserConditions;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/8.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser getByUsername(String userName) {
        AdminUserConditions adminUserConditions = new AdminUserConditions();
        adminUserConditions.createCriteria().andUsernameEqualTo(userName);
        List<AdminUser> list = adminUserDao.selectByExample(adminUserConditions);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public AdminUser getById(Integer userId) {
        return adminUserDao.selectById(userId);
    }
}
