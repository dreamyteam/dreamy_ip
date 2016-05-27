package com.dreamy.ipcool.controllers.about;

import com.dreamy.ipcool.controllers.IpcoolController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:45
 */
@RequestMapping("/about")
@Controller
public class AboutController extends IpcoolController {

    @RequestMapping("/us")
    public String us() {
        return "/about/us";
    }

    @RequestMapping("/feedback")
    public String feedback() {
        return "/about/feedback";
    }

    @RequestMapping("/agreement")
    public String agreement() {
        return "/about/agreement";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "/about/contact";
    }

}
