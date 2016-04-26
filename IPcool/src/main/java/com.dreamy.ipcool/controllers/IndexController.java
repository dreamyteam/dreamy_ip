package com.dreamy.ipcool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午4:30
 */
@Controller
@RequestMapping("/")
public class IndexController extends IpcoolController {

    @RequestMapping("")
    public String index() {
        return "/index";
    }

    @RequestMapping("/comprehensive")
    public String comprehensive() {
        return "/index/comprehensive";
    }

    @RequestMapping("/potential")
    public String potential() {
        return "/index/potential";
    }

    @RequestMapping("/heat")
    public String heat() {
        return "/index/heat";
    }

    @RequestMapping("/introduction")
    public String introduction() {
        return "/index/introduction";
    }

    @RequestMapping("/sum")
    public String base() {
        return "/index/sum";
    }

    @RequestMapping("/persona")
    public String personal() {
        return "/index/persona";
    }

    @RequestMapping("/propagation")
    public String propagation() {
        return "/index/propagation";
    }

    @RequestMapping("/user/reviews")
    public String userReviews() {
        return "/index/user_reviews";
    }


}
