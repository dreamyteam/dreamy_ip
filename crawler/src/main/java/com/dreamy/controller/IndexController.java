package com.dreamy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyongxing on 16/4/12.
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController {
    @RequestMapping("")
    public String index(HttpServletRequest request) {
        return "/index";
    }
}