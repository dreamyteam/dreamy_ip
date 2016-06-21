package com.dreamy.admin.controller.user;

import com.dreamy.admin.controller.DashboardBaseController;
import com.dreamy.beans.Page;
import com.dreamy.dao.iface.user.UserDao;
import com.dreamy.domain.user.User;
import com.dreamy.domain.user.UserAuth;
import com.dreamy.enums.UserAuthEnums;
import com.dreamy.service.iface.user.UserAuthService;
import com.dreamy.service.iface.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mac on 16/6/20.
 */
@Controller
@RequestMapping(value = "/userAuth")
public class ManageUserAuthController extends DashboardBaseController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @RequestMapping("/list")
    public String list(UserAuth userAuth, ModelMap map, Page page) {

        userAuth.setStatus(UserAuthEnums.status_apply_pass.getValue());
        if(userAuth.getType() == null) {
            userAuth.setType(UserAuthEnums.type_personal.getValue());
        }

        List<UserAuth> authList = userAuthService.getList(userAuth, page);

        map.put("list", authList);
        map.put("page",page);
        map.put("type", userAuth.getType());
        return "/user/auth/list";
    }

    @RequestMapping("/view")
    public String view(UserAuth userAuth, ModelMap map) {

        UserAuth auth = userAuthService.getUserAuthById(userAuth.getId());

        map.put("userAuth", auth);

        return "/user/auth/view";
    }

    @RequestMapping("/noAuthList")
    public String noAuthList(UserAuth userAuth, ModelMap map, Page page) {

        List<User> list = userAuthService.noAuthList(userAuth, page);

        map.put("list", list);
        map.put("page", page);
        return "/user/auth/no_auth_list";
    }

    @RequestMapping("/noAuthView")
    public String noAuthView(User user, ModelMap map) {

        User u = userService.getUserById(user.getId());

        map.put("user", u);

        return "/user/auth/no_auth_view";
    }

    @RequestMapping("/verifyList")
    public String verifyList(UserAuth userAuth, ModelMap map, Page page) {
        userAuth.setStatus(UserAuthEnums.status_applying.getValue());
        if(userAuth.getType() == null) {
            userAuth.setType(UserAuthEnums.type_personal.getValue());
        }
        List<UserAuth> authList = userAuthService.getList(userAuth, page);

        map.put("list", authList);
        map.put("page",page);
        map.put("type", userAuth.getType());
        return "/user/verifyAuth/list";
    }

    @RequestMapping(value = "/doVerify")
    public String doVerify(@RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "status", required = true) Integer status) {
        UserAuth userAuth = new UserAuth().id(id).status(status);
        userAuth = userAuthService.doVerify(userAuth);
        return redirect("/userAuth/verifyAuth/list?type="+userAuth.getType());
    }




}
