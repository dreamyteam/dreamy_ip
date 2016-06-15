package com.dreamy.service.impl.user;

import com.dreamy.dao.iface.user.UserAuthDao;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.domain.user.UserAuthConditions;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.enums.UserAuthEnums;
import com.dreamy.service.iface.user.UserAuthService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mac on 16/6/14.
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public UserAuth getUserAuthByUserId(Integer userId) {
        UserAuth userAuth = new UserAuth();
        UserAuthConditions conditions = new UserAuthConditions();
        conditions.createCriteria().andUserIdEqualTo(userId);
        List<UserAuth> list = userAuthDao.selectByExample(conditions);
        if(CollectionUtils.isNotEmpty(list)) {
            userAuth = list.get(0);
        }
        return userAuth;
    }

    @Override
    public void doAuthApply(UserAuth userAuth) {
        UserAuth ua =  getUserAuthByUserId(userAuth.getUserId());
        userAuth.setStatus(UserAuthEnums.status_applying.getValue());
        if(ua == null) {
            userAuthDao.save(userAuth);
        }else {
            userAuth.setId(ua.getId());
            userAuthDao.update(userAuth);
        }
    }

    @Override
    public ErrorCodeEnums doBusinessAuthCheckCode(Integer userId, String valideCode) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        String errorMsg = "";

        if(StringUtils.isEmpty(valideCode)) {
            errorMsg = "验证码不能为空!";
        }

        UserAuth ua =  getUserAuthByUserId(userId);
        if(ua != null && ua.getId() > 0) {
            if(UserAuthEnums.status_applying.getValue().equals(ua.getStatus()) && StringUtils.isNotEmpty(ua.getValideCode())) {
                if(valideCode.equals(ua.getValideCode())) {
                    ua.setStatus(UserAuthEnums.status_apply_pass.getValue());
                    userAuthDao.update(ua);
                }else {
                    errorMsg = "验证码不正确!";
                }
            }else if(UserAuthEnums.status_applying.getValue().equals(ua.getStatus()) && StringUtils.isEmpty(ua.getValideCode())) {
                errorMsg = "请等待客服审核通过后,再进行验证!";
            }else {
                errorMsg = "验证已经处理,请勿重复验证!";
            }
        }else {
            errorMsg = "您还未提交认证,请提交后,再进行验证!";
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.auth_apply_failed;
            errorCodeEnums.setErrorMsg(errorMsg);
        }

        return errorCodeEnums;
    }

}