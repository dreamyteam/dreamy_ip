package com.dreamy.service.admin;

import com.dreamy.domain.admin.AdminUser;

/**
 * Created by wangyongxing on 16/4/8.
 */
public interface AdminUserService {

    public AdminUser getByUsername(String userName);

    public  AdminUser getById(Integer userId);
}
