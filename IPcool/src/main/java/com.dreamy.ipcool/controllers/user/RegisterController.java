package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.params.RegisterParam;
import com.dreamy.domain.user.User;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ShortMessageService;
import com.dreamy.service.iface.VerificationCodeService;
import com.dreamy.service.iface.user.UserService;
import com.dreamy.service.impl.user.RegisterServiceImpl;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PasswordUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:42
 */
@Controller
public class RegisterController extends IpcoolController {

    @Autowired
    private RegisterServiceImpl registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private ShortMessageService shortMessageService;


    @RequestMapping(value = "/user/register/verificationCode")
    @ResponseBody
    public void getVerificationCode(RegisterParam param, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();

        AsynchronousService.submit(new ObjectCallable(param.getMobile()) {
            @Override
            public Object run() throws Exception {
                String code = verificationCodeService.createVerificationCode(4);
                if (StringUtils.isNotEmpty(code)) {
                    verificationCodeService.saveCodeToCache(name, code);
                    shortMessageService.send(name, "【IP库】您的验证码是" + code);
                }
                return null;
            }
        });


        interfaceReturn(response, JsonUtils.toString(bean), "");
    }


    @RequestMapping(value = "/user/register")
    @ResponseBody
    public void register(RegisterParam param, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        ErrorCodeEnums errorCodeEnums = registerService.checkRegisterParam(param);
        if (errorCodeEnums.getErrorCode() > 0) {
            bean.failure(errorCodeEnums);
        } else {
            User user = userService.getUserByMobile(param.getMobile());
            if (user.getId() == null) {
                user.phone(param.getMobile());
                user.userName(param.getMobile());
                user.password(PasswordUtils.createPassword(param.getPassword()));
                user.userKey(registerService.createUserKey(param));

                userService.save(user);
            } else {
                errorCodeEnums = ErrorCodeEnums.register_failed;
                errorCodeEnums.setErrorMsg("用户已存在！");
                bean.failure(errorCodeEnums);
            }
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
