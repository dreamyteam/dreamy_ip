package com.dreamy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyongxing on 16/4/12.
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "/index";
    }
}