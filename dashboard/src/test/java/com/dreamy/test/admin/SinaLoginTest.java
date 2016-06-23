//package com.dreamy.test.admin;
//
//import com.dreamy.admin.service.SinaLoginService;
//import com.dreamy.service.cache.CommonService;
//import com.dreamy.test.BaseJunitTest;
//import org.junit.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.Resource;
//
///**
// * Created by wangyongxing on 16/5/11.
// */
//public class SinaLoginTest extends BaseJunitTest {
//
//    @Autowired
//    SinaLoginService sinaLoginService;
//
//
//    @Resource
//    CommonService commonService;
//
//
//    @org.junit.Test
//    public void login() throws Exception {
//       // sinaLoginService.init();
//       String st= (String) commonService.getCacheService().get("cookie1");
//        System.out.println(st);
//        System.out.println(111);
//    }
//}
