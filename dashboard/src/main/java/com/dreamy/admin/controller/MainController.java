package com.dreamy.admin.controller;

import com.dreamy.domain.admin.SysModel;
import com.dreamy.service.admin.SysModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
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
        Map<Integer, Object[]> models = sysModelService.getSysModelMapByUserId(userId);
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Integer, Object[]> entry : models.entrySet()) {

            Object[] arr = entry.getValue();
            SysModel sysModel = (SysModel) arr[0];
            List<SysModel> list = (List<SysModel>) arr[1];
            if (list != null) {
                str.append("<dd><div class=\"title\"><span><img src=" + sysModel.getImg() + " /></span>" + sysModel.getName() + "</div>");
                str.append(" <ul class=\"menuson\">");
                for (SysModel model1 : list) {
                    str.append("<li><cite></cite><a href=" + model1.getUrl() + " target=\"mainFrame\">" + model1.getName() + "</a><i></i></li>");
                }
                str.append("</ul>");
            }

            str.append("</dd>");

        }
        System.out.println(str.toString());

        model.put("str", str.toString());
        return "/admin/main/left";
    }

    /**
     * 右侧
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/nav")
    public String nav() {

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/admin/main/main";
    }
}
