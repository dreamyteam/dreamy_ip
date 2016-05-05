package com.dreamy.ipcool.controllers.homepage;

import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.cache.RedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 上午10:37
 */
@Controller
@RequestMapping(value = {"/"})
public class HomepageController extends IpcoolController {


    @RequestMapping("")
    public String index() {
        return "/homepage/homepage";
    }
}
