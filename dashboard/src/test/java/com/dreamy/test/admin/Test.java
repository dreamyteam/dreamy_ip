package com.dreamy.test.admin;

import com.dreamy.dao.iface.admin.AdminUserDao;
import com.dreamy.dao.iface.admin.RoleDao;
import com.dreamy.dao.iface.admin.SysModelDao;
import com.dreamy.dao.iface.admin.UserRoleDao;
import com.dreamy.dao.iface.ipcool.BookIndexHistoryDao;
import com.dreamy.domain.admin.AdminUser;
import com.dreamy.domain.admin.Role;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.domain.admin.UserRole;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.service.cache.CacheCommonService;
import com.dreamy.service.iface.admin.SysModelService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.test.BaseJunitTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/3/31.
 */
public class Test extends BaseJunitTest {

    @Resource
    private SysModelDao modelDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    SysModelService sysModelService;
    @Resource
    AdminUserDao adminUserDao;
    @Resource
    CacheCommonService commonService;
    @Resource
    BookViewService bookViewService;


    @Autowired
    BookIndexHistoryDao bookIndexHistoryDao;

    @org.junit.Test
    public void insertSysModel() {
        SysModel sysModel = new SysModel().img("").parentId(1).url("").name("系统管理").isUse(1).orderId(1);
        modelDao.save(sysModel);
        System.out.println(11);

    }

    @org.junit.Test
    public void insetRole() {
        Role role = new Role().displayOrder(0).isSys(0).status(0).name(" 测试管理员").indexUrl("");
        roleDao.save(role);
        System.out.println(111);

    }

    @org.junit.Test
    public void insertUserRole() {
        UserRole userRole = new UserRole().roleId(2).adminId(1);
        userRoleDao.save(userRole);
    }

    @org.junit.Test
    public void find() {
        List<SysModel> list = sysModelService.getByUserId(2);
        System.out.println(1212);
        list = sysModelService.getAll();
    }

    @org.junit.Test
    public void insertAdminUuser() {
        AdminUser adminUser = new AdminUser().userName("test");
        adminUserDao.save(adminUser);
        commonService.getCacheService().put("1", "1");


    }

    @org.junit.Test
    public void findMax() {
        BookIndexHistory bookIndexHistory= bookIndexHistoryDao.selectMaxByBookId(110);
        System.out.println(1);


    }

    @org.junit.Test
    public void insertBookIndexHistory() {
        BookView bookView = bookViewService.getById(3);
        BookIndexHistory history=new BookIndexHistory();
        history.activityIndex(bookView.getActivityIndex());
        history.developIndex(bookView.getDevelopIndex());
        history.propagateIndex(bookView.getPropagateIndex());
        history.hotIndex(bookView.getHotIndex());
        history.compositeIndex(bookView.getCompositeIndex());
        history.bookId(bookView.getBookId());
        history.status(0);
        bookIndexHistoryDao.save(history);




    }


}
