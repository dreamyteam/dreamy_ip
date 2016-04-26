package com.dreamy.ipcool.controllers.apply;

import com.dreamy.ipcool.controllers.IpcoolController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/26
 * Time: 下午7:22
 */
@Controller
@RequestMapping("/apply")
public class ApplyController extends IpcoolController{

    @RequestMapping("")
    public String apply(){
        return "/apply/apply";
    }

    @RequestMapping("/result")
    public String applyResult(){
        return "/apply/result";
    }


}
