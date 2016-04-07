package com.dreamy.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Controller
@RequestMapping(value = {"/admin/login", "/"})
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {

        return "/admin/main/login";
    }
}
