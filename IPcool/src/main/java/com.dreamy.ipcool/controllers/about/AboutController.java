package com.dreamy.ipcool.controllers.about;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.ipcool.controllers.IpcoolController;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookScoreService bookScoreService;
    @Autowired
    private BookViewService bookViewService;

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

    @RequestMapping("/tt")
    public void tt() {
        BookView bookView = bookViewService.getByBookId(7518);
        String score = bookScoreService.getDevelopIndexByRecord(bookView);
        System.err.println("111");
    }



}
