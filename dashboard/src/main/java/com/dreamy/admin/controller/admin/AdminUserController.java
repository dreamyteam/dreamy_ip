package com.dreamy.admin.controller.admin;

import com.dreamy.admin.controller.DashboardController;
import com.dreamy.beans.Page;
import com.dreamy.beans.UserSession;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.domain.admin.Role;
import com.dreamy.domain.admin.UserRole;
import com.dreamy.service.iface.admin.AdminUserService;
import com.dreamy.service.iface.admin.RoleService;
import com.dreamy.service.iface.admin.UserRoleService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.HashUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/15.
 */
@Controller
@RequestMapping(value = "/adminUser")
public class AdminUserController extends DashboardController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;

    /**
     *
     *
     * @return
     */
    @RequestMapping("")
    public String role(AdminUser adminUser, HttpServletRequest request, ModelMap model, Page page) {
        List<AdminUser> list = adminUserService.getList(adminUser, page);
        model.put("list", list);
        model.put("page",page);
        return "/admin/adminUser/adminUser";
    }

    @RequestMapping(value = "/view")
    public String view(@RequestParam(value = "id", required = false,defaultValue ="0") Integer id, ModelMap model) {
        if(id>0) {
            AdminUser adminUser = adminUserService.getById(id);
            model.put("adminUser", adminUser);
            List<UserRole> userRoles=userRoleService.getUserToRoleList(adminUser.getId());
            if(CollectionUtils.isNotEmpty(userRoles))
            {
                model.put("roleId",userRoles.get(0).getRoleId());
            }

        }
        List<Role> list=roleService.getRoleList(null);
        model.put("list", list);
        return "/admin/adminUser/adminUser-view";
    }



    /**
     *
     *
     * @return
     */
    @RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
    public String saveUpdate(AdminUser adminUser,@RequestParam(value = "roleId", required = true,defaultValue = "0")Integer roleId) {
        if(adminUser.getId()!=null)
        {
            adminUserService.update(adminUser,roleId);
        }
        else{
            adminUser.isSys(1);
            adminUser.password(HashUtils.md5("123456"));
            adminUserService.save(adminUser,roleId);
        }
        return redirect("/adminUser.html");
    }

    /**
     *密码重置
     * @return
     */
    @RequestMapping(value = "/resetPwd")
    public String resetPwd(@RequestParam(value = "id", required = true)Integer id) {
       AdminUser adminUser=new AdminUser().id(id).password(HashUtils.md5("123456"));
        adminUserService.updatePassword(adminUser);
        return redirect("/adminUser.html");
    }


    @RequestMapping(value = "/changePwd")
    public String changePwd(HttpServletRequest request)
    {

        return "/admin/main/changePwd";
    }

    @RequestMapping(value = "/changePwdSave")
    public String changePwdSave(HttpServletRequest request,@RequestParam(value = "newPwd", required = true)String newPwd)
    {
        UserSession session = getUserSession(request);
        int userId=session.getUserId();
        AdminUser adminUser=new AdminUser().id(userId).password(HashUtils.md5(newPwd));
        adminUserService.updatePassword(adminUser);
        String sid = getUserSessionId(request);
        if (StringUtils.isNotEmpty(sid)) {
            clearUserSession(request);
            userSessionContainer.clear(sid);
        }
        return "/admin/main/login";
    }

}
