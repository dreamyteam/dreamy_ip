package com.dreamy.admin.controller.crawler;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by wangyongxing on 16/4/25.
 */
@Controller
@RequestMapping(value = "/popular")
public class PopularController extends DashboardController {

    /**
     * @return
     */
    @RequestMapping("")
    public String role(IpBook ipBook, ModelMap model, Page page) {
        return "/crawler/netbook/ipbook";
    }

}
