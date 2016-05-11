package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.beans.params.IpApplyParams;
import com.dreamy.dao.iface.user.UserApplyDao;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserApply;
import com.dreamy.domain.user.UserApplyConditions;
import com.dreamy.service.iface.ipcool.UserApplyService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/11
 * Time: 上午10:17
 */
@Service
public class UserApplyServiceImpl implements UserApplyService {

    @Autowired
    private UserApplyDao userApplyDao;

    @Override
    public Integer save(IpApplyParams params, User user) {

        UserApply userApply = new UserApply()
                .name(params.getIpName())
                .type(params.getIpType())
                .refUrl(params.getRefUrl())
                .ip(params.getIpAddress())
                .userId(user.getId())
                .userName(user.getUserName());

        return userApplyDao.save(userApply);
    }

    @Override
    public UserApply getByName(String name) {
        UserApply userApply = new UserApply();

        if (StringUtils.isNotEmpty(name)) {
            UserApplyConditions conditions = new UserApplyConditions();
            conditions.createCriteria().andNameEqualTo(name);

            Page page = new Page();
            page.setPageSize(1);

            conditions.setPage(page);

            List<UserApply> userApplies = userApplyDao.selectByExample(conditions);
            if (CollectionUtils.isNotEmpty(userApplies)) {
                userApply = userApplies.get(0);
            }
        }

        return userApply;
    }
}
