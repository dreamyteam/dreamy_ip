package com.dreamy.service.impl.user;

import com.dreamy.beans.params.RegisterParam;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.enums.RegexEnums;
import com.dreamy.service.iface.user.RegisterService;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午12:23
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Override
    public ErrorCodeEnums checkRegisterParam(RegisterParam param) {
        ErrorCodeEnums errorCodeEnums = ErrorCodeEnums.success;

        //空值判断
        if (StringUtils.isEmpty(param.getMobile())) {
            errorCodeEnums = ErrorCodeEnums.login_failed;
            errorCodeEnums.setErrorMsg("手机号码不能为空！");
        } else if (StringUtils.isEmpty(param.getPassword())) {
            errorCodeEnums = ErrorCodeEnums.login_failed;
            errorCodeEnums.setErrorMsg("密码不能为空！");
        } else if (StringUtils.isEmpty(param.getCheckCode())) {
            errorCodeEnums = ErrorCodeEnums.login_failed;
            errorCodeEnums.setErrorMsg("验证码不能为空！");
        }

        if (errorCodeEnums.getErrorCode() == 0) {
            if (!Pattern.matches(RegexEnums.mobile.getRegex(), param.getMobile())) {
                errorCodeEnums = ErrorCodeEnums.login_failed;
                errorCodeEnums.setErrorMsg("手机号码格式不正确！");
            }
        }


        return errorCodeEnums;
    }
}
