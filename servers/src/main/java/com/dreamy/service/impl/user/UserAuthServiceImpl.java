package com.dreamy.service.impl.user;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.user.UserAuthDao;
import com.dreamy.dao.iface.user.UserDao;
import com.dreamy.dao.iface.user.UserPartDao;
import com.dreamy.domain.user.*;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.enums.UserAuthEnums;
import com.dreamy.service.iface.ShortMessageService;
import com.dreamy.service.iface.VerificationCodeService;
import com.dreamy.service.iface.user.UserAuthService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dreamy.enums.UserAuthEnums.*;

/**
 * Created by mac on 16/6/14.
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;
    @Autowired
    private UserPartDao userPartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private ShortMessageService shortMessageService;

    @Override
    public UserAuth getUserAuthByUserId(Integer userId) {
        UserAuth userAuth = userAuthDao.getUserAuthByUserId(userId);
        userAuth = getPartStr(userAuth);
        return userAuth;
    }

    @Override
    public void doAuthApply(UserAuth userAuth) {
        UserAuth ua =  userAuthDao.getUserAuthByUserId(userAuth.getUserId());
        userAuth.setStatus(status_applying.getValue());
        userAuth.setValideCode("");
        if(ua == null || ua.getId() == null) {
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

        UserAuth ua =  userAuthDao.getUserAuthByUserId(userId);
        if(ua != null && ua.getId() > 0) {
            if(status_applying.getValue().equals(ua.getStatus()) && StringUtils.isNotEmpty(ua.getValideCode())) {
                if(valideCode.equals(ua.getValideCode())) {
                    ua.setStatus(status_apply_pass.getValue());
                    userAuthDao.update(ua);
                }else {
                    errorMsg = "验证码不正确!";
                }
            }else if(status_applying.getValue().equals(ua.getStatus()) && StringUtils.isEmpty(ua.getValideCode())) {
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

    @Override
    public List<UserAuth> getList(UserAuth userAuth, Page page) {
        Map<String,Object> params= BeanUtils.toQueryMap(userAuth);
        UserAuthConditions conditions = new UserAuthConditions();
        if(userAuth.getStatus() == UserAuthEnums.status_applying.getValue()) {
            conditions.createCriteria().addByMap(params).andValideCodeEqualTo("");
        }else {
            conditions.createCriteria().addByMap(params);
        }
        if(page != null) {
            page.setTotalNum(userAuthDao.countByExample(conditions));
            conditions.setPage(page);
        }
        List<UserAuth> list = userAuthDao.selectByExample(conditions);
        if(list != null && list.size() > 0) {
            for (int i=0; i<list.size(); i++) {
                UserAuth auth = getPartStr(list.get(i));
            }
        }
        return list;
    }

    @Override
    public List<User> noAuthList(UserAuth userAuth, Page page) {
        UserAuthConditions conditions = new UserAuthConditions();
        conditions.createCriteria().andStatusEqualTo(UserAuthEnums.status_apply_pass.getValue());
        List<UserAuth> authList = userAuthDao.selectByExample(conditions);
        List<Integer> l = new ArrayList<>();
        for(UserAuth auth : authList) {
            l.add(auth.getUserId());
        }
        UserConditions userConditions = new UserConditions();
        if(l.size() > 0) {
            userConditions.createCriteria().andIdNotIn(l);
        }
        List<User> list = userDao.selectByExample(userConditions);
        return list;
    }

    @Override
    public UserAuth getUserAuthById(Integer id) {
        UserAuth userAuth = userAuthDao.selectById(id);
        userAuth = getPartStr(userAuth);
        return userAuth;
    }

    @Override
    public UserAuth doVerify(UserAuth userAuth) {
        UserAuth auth = userAuthDao.selectById(userAuth.getId());
        if(auth.getStatus() == UserAuthEnums.status_applying.getValue()) {
            if(auth.getType() == UserAuthEnums.type_business.getValue() && userAuth.getStatus() == UserAuthEnums.status_apply_pass.getValue()) {
                String code = verificationCodeService.createVerificationCode(4);
                if (StringUtils.isNotEmpty(code)) {
                    shortMessageService.send(auth.getPhone(), "【IP库】您的验证码是" + code);
                }
                auth.setValideCode(code);
            }else {
                auth.setStatus(userAuth.getStatus());
            }
            userAuthDao.update(auth);
        }
        return auth;
    }

    @Override
    public List<UserAuth> getBusinessAuthList(UserAuth userAuth, Page page) {
        UserAuthConditions conditions = new UserAuthConditions();
        conditions.createCriteria().andTypeEqualTo(userAuth.getType()).andStatusNotEqualTo(UserAuthEnums.status_apply_not_pass.getValue());
        if(page != null) {
            page.setTotalNum(userAuthDao.countByExample(conditions));
            conditions.setPage(page);
        }
        List<UserAuth> list = userAuthDao.selectByExample(conditions);
        if(list != null && list.size() > 0) {
            for (int i=0; i<list.size(); i++) {
                UserAuth auth = getPartStr(list.get(i));
            }
        }
        return list;
    }

    /**
     * 将角色由id转换成文字
     * @param userAuth
     * @return
     */
    public UserAuth getPartStr(UserAuth userAuth) {
        if(userAuth != null && userAuth.getId() != null && userAuth.getId() > 0 && StringUtils.isNotEmpty(userAuth.getPart())) {
            List<Integer> l = new ArrayList<Integer>();
            String[] str = userAuth.getPart().split(",");
            for(int i=0; i<str.length; i++) {
                l.add(Integer.parseInt(str[i]));
            }
            UserPartConditions userPartConditions = new UserPartConditions();
            userPartConditions.createCriteria().andIdIn(l);
            List<UserPart> partList = userPartDao.selectByExample(userPartConditions);
            StringBuffer parts = new StringBuffer();
            for(UserPart up : partList) {
                parts.append(up.getName()+"  ");
            }
            userAuth.setPart(parts.toString().trim());
        }
        return userAuth;
    }

}