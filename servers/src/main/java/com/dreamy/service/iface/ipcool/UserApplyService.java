package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.params.IpApplyParams;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserApply;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 上午10:16
 */
public interface UserApplyService {

    /**
     *
     * @param params
     * @param user
     * @return
     */
    Integer save(IpApplyParams params,User user);

    /**
     *
     * @param name
     * @return
     */
    UserApply getByName(String name);
}
