package com.dreamy.admin.controller.algorithm;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.Algorithm;
import com.dreamy.service.iface.ipcool.AlgorithmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/19.
 */
@Controller
@RequestMapping(value = "/algorithm")
public class AlgorithmController extends DashboardController {

    @Resource
    private AlgorithmService algorithmService;

    @RequestMapping(value = "")
    public String list(Algorithm algorithm, HttpServletRequest request, ModelMap model, Page page){

        List<Algorithm> list=algorithmService.getList(algorithm,page);
        model.put("list",list);

        return "/algorithm/algorithm-list";

    }
}
