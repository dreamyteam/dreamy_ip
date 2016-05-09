package com.dreamy.ipcool.controllers.user;

import com.dreamy.ipcool.controllers.IpcoolController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:37
 */
@RequestMapping("user")
@Controller
public class UserController extends IpcoolController {


    @RequestMapping("/account")
    public String account() {
        return "/user/account";
    }

    @RequestMapping("/bio")
    public String bio() {
        return "/user/bio";
    }

    @RequestMapping("/following")
    public String followList() {
        return "/user/following";
    }

    @RequestMapping("/view/history")
    public String history() {
        return "/user/history";
    }

    @RequestMapping("/modify/password")
    public String modifyPassword() {
        return "/user/password_modify";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "/homepage/homepage";
    }
}
