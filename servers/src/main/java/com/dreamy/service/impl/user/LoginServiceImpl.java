package com.dreamy.service.impl.user;

import com.dreamy.beans.params.LoginParam;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.service.iface.user.LoginService;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:14
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public ErrorCodeEnums checkLoginParam(LoginParam param) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;
        String errorMsg = "";

        //空值判断
        if (StringUtils.isEmpty(param.getMobile())) {
            errorMsg = ("手机号码不能为空！");
        } else if (StringUtils.isEmpty(param.getPassword())) {
            errorMsg = ("密码不能为空！");
        }

        if (StringUtils.isEmpty(errorMsg)) {
            Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[6-8])|(18[0-9]))\\d{8}$");
            Matcher matcher = pattern.matcher(param.getMobile());

            if (!matcher.matches()) {
                errorMsg = ("手机号码格式不正确！");
            }
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            errorCodeEnums = ErrorCodeEnums.login_failed;
            errorCodeEnums.setErrorMsg(errorMsg);
        }

        return errorCodeEnums;
    }
}
