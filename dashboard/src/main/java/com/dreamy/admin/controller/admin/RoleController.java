package com.dreamy.admin.controller.admin;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.domain.admin.Role;
import com.dreamy.domain.admin.RoleModel;
import com.dreamy.service.iface.admin.RoleModelService;
import com.dreamy.service.iface.admin.RoleService;
import com.dreamy.service.iface.admin.SysModelService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends DashboardController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleModelService roleModelService;

    @Resource
    private SysModelService sysModelService;


    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String role(HttpServletRequest request, ModelMap model) {
        List<Role> list = roleService.getRoleList(null);
        model.put("list", list);
        return "/admin/role/role";
    }


    /**
     * 查看角色
     *
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
        if (id != null) {
            Role role = roleService.getRoleById(id);
           if(id<=1)
           {
               id=0;
           }
            Set<Integer> list = roleModelService.getRoleModelList(id);
            model.put("role", role);
            model.put("list", list);
        }
        //获取模块
        Map<Integer, Object[]> data = sysModelService.getSysModelMapByUserId(1);
        model.put("data", data);

        return "/admin/role/role-view";
    }


    /**
     * 修改角色
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Role role, @RequestParam(value = "ids", required = true) List<Integer> ids) {
        role.isSys(1);
        if (role.getId() != null) {
            roleService.update(role);
        } else {
            roleService.save(role);
        }
        //模块保存
        roleModelService.deleteByRoleId(role.getId());
        if (CollectionUtils.isNotEmpty(ids)) {

            for (Integer modeId : ids) {
                RoleModel model = new RoleModel();
                model.setModelId(modeId);
                model.setRoleId(role.getId());
                roleModelService.save(model);
            }
        }
        return redirect("/role.html");
    }

    /**
     * 删除角色
     *
     * @return
     */
    @RequestMapping(value = "/del")
    public String del( @RequestParam(value = "id", required = true) Integer id) {
        Role role=new Role().status(-1).id(id);
        roleService.update(role);
        return redirect("/role.html");
    }

    /**
     * 停用角色
     *
     * @return
     */
    @RequestMapping(value = "/disable")
    public String disable(@RequestParam(value = "id", required = true) Integer id) {
        Role role=new Role().status(2).id(id);
        roleService.update(role);
        return redirect("/role.html");
    }


    /**
     * 启用角色
     *
     * @return
     */
    @RequestMapping(value = "/enable")
    public String enable(@RequestParam(value = "id", required = true) Integer id) {
        Role role=new Role().status(1).id(id);
        roleService.update(role);
        return redirect("/role.html");
    }

}


