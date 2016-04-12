package com.dreamy.admin.controller.literary;

import com.dreamy.admin.controller.DashboardBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangyongxing on 16/4/12.
 *
 * 文学
 */

@Controller
@RequestMapping(value = "/literary")
public class LiteraryController extends DashboardBaseController {
    /**
     * 出版文学
     * @return
     */
    @RequestMapping(value = "/publish")
    public String publish(){


        return "/literary/publish";

    }

    @RequestMapping(value = "/netWork")
    public String netWork(){


        return "/literary/netWork";

    }

}
