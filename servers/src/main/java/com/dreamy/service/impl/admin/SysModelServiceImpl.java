package com.dreamy.service.impl.admin;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.admin.SysModelDao;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.domain.admin.SysModelConditions;
import com.dreamy.domain.admin.UserRole;
import com.dreamy.service.iface.admin.SysModelService;
import com.dreamy.service.iface.admin.UserRoleService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Service
public class SysModelServiceImpl implements SysModelService {
    @Resource
    private SysModelDao sysModelDao;
    @Resource
    private UserRoleService userRoleService;




    @Override
    public List<SysModel> getSysModelPage(SysModel sysModel, Page page) {

        new HashMap<String, Object>();
        Map<String, Object> params = BeanUtils.toQueryMap(sysModel);
        SysModelConditions conditions = new SysModelConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(sysModelDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return sysModelDao.selectByExample(conditions);

    }

    @Override
    public List<SysModel> queryByRoles(List<Integer> roles) {
        List<SysModel> list=new ArrayList<SysModel>();
        if(CollectionUtils.isNotEmpty(roles)) {
            list=sysModelDao.selectByRoles(roles);
        }
        return list;
    }

    @Override
    public List<SysModel> getByUserId(Integer userId) {
//       List<UserRole> list=userRoleService.getUserToRoleList(userId);
//        List<Integer> roles = new ArrayList<Integer>();
//        for (UserRole roleUser : list) {
//            roles.add(roleUser.getRoleId());
//        }
//        return queryByRoles(roles);
        List<SysModel> sysModels=new ArrayList<>();
        if(userId==1)
        {
            sysModels=getAll();
        }
        else{
            sysModels= getByUserId(userId);
        }
        return sysModels;
    }

    @Override
    public List<SysModel> getAll() {
        return sysModelDao.selectByExample(null);
    }

    @Override
    public Map<Integer, Object[]> getSysModelMapByUserId(int userId) {

        List<SysModel> sysModels=new ArrayList<>();
        if(userId==1)
        {
           sysModels=getAll();
        }
        else{
            sysModels= getByUserId(userId);
        }

        Map<Integer, Object[]> map= buildMap(sysModels);

        return map;
    }

    private  Map<Integer, Object[]> buildMap(List<SysModel> sysModels) {
        Set<String> urlSet = new HashSet<String>();
        Map<Integer, Object[]> map = new LinkedHashMap<Integer, Object[]>();
        //一级菜单
        for (SysModel model : sysModels) {
            if (model.getParentId()<=0) {
                map.put(model.getId(), new Object[]{model, new ArrayList<SysModel>() });
            }
        }
        //二级菜单
        for (SysModel model : sysModels) {
            if (model.getParentId()>0) {
                if (map.containsKey(model.getParentId())) {
                    Object[] data = map.get(model.getParentId());
                    ((List<SysModel>)data[1]).add(model);
                    //加入权限
                    String url = model.getUrl();
                    urlSet.add(url);
                }
            }

        }
        return map;


    }


}
