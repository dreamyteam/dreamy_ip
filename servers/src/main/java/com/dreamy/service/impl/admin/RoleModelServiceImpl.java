package com.dreamy.service.impl.admin;

import com.dreamy.dao.iface.admin.RoleModelDao;
import com.dreamy.domain.admin.RoleModel;
import com.dreamy.domain.admin.RoleModelConditions;
import com.dreamy.service.iface.admin.RoleModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class RoleModelServiceImpl implements RoleModelService {
    @Resource
    private RoleModelDao roleModelDao;

    @Override
    public Set<Integer> getRoleModelList(Integer roleId) {
        List<RoleModel> list=new ArrayList<RoleModel>();
        if(roleId>0) {
            RoleModelConditions conditions = new RoleModelConditions();
            conditions.createCriteria().andRoleIdEqualTo(roleId);
            list= roleModelDao.selectByExample(conditions);
        }
        else
        {
            list=roleModelDao.selectByExample(null);
        }
        Set<Integer> ids = new HashSet<Integer>();
        for (RoleModel roleModel : list) {
            ids.add(roleModel.getModelId());
        }

        return ids;
    }

    @Override
    public int deleteByRoleId(Integer roleId) {

        RoleModelConditions conditions = new RoleModelConditions();
        conditions.createCriteria().andRoleIdEqualTo(roleId);
        roleModelDao.deleteByExample(conditions);
        return roleModelDao.deleteByExample(conditions);


    }

    @Override
    public RoleModel save(RoleModel roleModel) {
        roleModelDao.save(roleModel);
        return roleModel;
    }
}
