package com.dreamy.ipcool.controllers.rank;

import com.dreamy.ipcool.controllers.IpcoolController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:06
 */
@RequestMapping("/rank")
@Controller
public class RankController extends IpcoolController {

    @RequestMapping("/list")
    public String list() {
        return "/rank/list";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "/rank/detail";
    }
}
