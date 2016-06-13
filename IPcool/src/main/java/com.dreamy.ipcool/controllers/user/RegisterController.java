package com.dreamy.ipcool.controllers.user;

import com.dreamy.beans.InterfaceBean;
import com.dreamy.beans.UserSession;
import com.dreamy.beans.params.RegisterParams;
import com.dreamy.domain.user.User;
import com.dreamy.enums.ErrorCodeEnums;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ShortMessageService;
import com.dreamy.service.iface.VerificationCodeService;
import com.dreamy.service.iface.user.RegisterService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午2:42
 */
@Controller
public class RegisterController extends IpcoolController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private ShortMessageService shortMessageService;


    @RequestMapping(value = "/user/register/verificationCode")
    @ResponseBody
    public void getVerificationCode(RegisterParams param, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        String mobile = param.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            bean.failure(ErrorCodeEnums.get_verification_code_failed.getErrorCode(), "手机号码不能为空");
        } else {
//            User user = userService.getUserByMobile(param.getMobile());
//            if (user.getId() != null) {
//            bean.failure(ErrorCodeEnums.get_verification_code_failed.getErrorCode(),"手机号码已经存在");
//            } else {
                AsynchronousService.submit(new ObjectCallable(mobile) {
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
//            }
        }


        interfaceReturn(response, JsonUtils.toString(bean), "");
    }


    @RequestMapping(value = "/user/register")
    @ResponseBody
    public void register(RegisterParams param, HttpServletRequest request, HttpServletResponse response) {
        InterfaceBean bean = new InterfaceBean().success();
        ErrorCodeEnums errorCodeEnums = registerService.checkRegisterParam(param);
        if (errorCodeEnums.getErrorCode() > 0) {
            bean.failure(errorCodeEnums);
        } else {
            User user = userService.getUserByMobile(param.getMobile());
            if (user.getId() == null) {
                user.phone(param.getMobile());
                user.userName(param.getUserName());
                user.password(PasswordUtils.createPassword(param.getPassword()));
                user.userKey(registerService.createUserKey(param));

                userService.save(user);

                UserSession session = new UserSession();
                session.setUserId(user.getId());
                session.setUsername(user.getUserName());
                session.setUserKey(user.getUserKey());
                session.setImageUrl(user.getImageUrl());
                session.setInfo(user.getInfo());
                session.setSex(user.getSex());

                userSessionContainer.set(getUserSessionId(request), session);
            } else {
                errorCodeEnums = ErrorCodeEnums.register_failed;
                errorCodeEnums.setErrorMsg("用户已存在！");
                bean.failure(errorCodeEnums);
            }
        }

        interfaceReturn(response, JsonUtils.toString(bean), "");
    }
}
