package com.dreamy.admin.controller.sys;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.dao.iface.sys.SysOptionDao;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.domain.sys.SysOption;
import com.dreamy.enums.IpBookStatusEnums;
import com.dreamy.service.iface.sys.SysOptionService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangyongxing on 16/5/4.
 */
@Controller
@RequestMapping("/sysoption")
public class SysOptionController extends DashboardController {

    @Resource
    private SysOptionService sysOptionService;
    /**
     * @return
     */
    @RequestMapping("")
    public String list(SysOption sysOption, ModelMap model, Page page) {

        List<SysOption> list = sysOptionService.getList(sysOption, page);

        model.put("list", list);
        model.put("page", page);

        return "/sys/sysoption_list";
    }
    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "id", required = true) Integer id, ModelMap model) {

        SysOption sysOption=sysOptionService.getById(id);
        model.put("sysOption", sysOption);
        return "/sys/sysoption_edit";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(SysOption sysOption, ModelMap model) {
        sysOptionService.update(sysOption);
        return redirect("/sysoption");
    }




}
