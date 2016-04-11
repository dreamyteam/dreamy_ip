package com.dreamy.admin.controller;

import com.dreamy.dao.admin.RoleDao;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.service.admin.SysModelService;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/1.
 */
@Controller
@RequestMapping(value = "/admin")
public class MainController extends DashboardController {

    @Resource
    private SysModelService sysModelService;

    /**
     * 左侧
     *
     * @return
     */
    @RequestMapping(value = "/left")
    public String left(HttpServletRequest request, ModelMap model) {
        int userId = getUserSession(request).getUserId();
        Map<Integer, Object[]> models = sysModelService.findFunctionByUserId(userId);
        model.put("models", models);

        return "/admin/main/left";
    }

    /**
     * 右侧
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/nav")
    public String nav(){

        return "/admin/main/subsystemright";


    }

    /**
     * 右侧
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/top")
    public String top() throws IOException {

        return "/admin/main/top";


    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "/admin/main/subsystemindex";
    }
}
