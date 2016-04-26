package com.dreamy.ipcool.controllers;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.service.iface.ipcool.BookViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午4:30
 */
@Controller
@RequestMapping(value = {"/login", "/"})
public class IndexController extends IpcoolController {

    @Resource
    private BookViewService bookViewService;

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

    /**
     * 详情页
     *
     * @param ipId
     * @return
     */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "ipId", required = true, defaultValue = "0") Integer ipId, ModelMap model) {
        BookView bookView = bookViewService.getById(ipId);
        model.put("view",bookView);
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
