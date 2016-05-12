package com.dreamy.admin.service.impl;

import com.dreamy.admin.service.RoleNavService;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.service.iface.admin.SysModelService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/20
 * Time: 上午11:41
 */
@Service
public class RoleNavServiceImpl implements RoleNavService {
    @Resource
    private SysModelService sysModelService;

    @Override
    public Map<String, Map<String, String>> getLeftNavsByRoleId(Integer roleId) {
        Map<String, Map<String, String>> navsTemp = new HashMap<>();

        List<SysModel> sysModels = sysModelService.getByUserId(1);
        if (CollectionUtils.isEmpty(sysModels)) {
            return navsTemp;
        }

        for (SysModel sysModel : sysModels) {
            Map<String, String> tmp = new HashMap<>();

            Integer parentId = sysModel.getParentId();
            if (!navsTemp.containsKey("" + parentId)) {
                if (parentId == 0) {
                    tmp.put(sysModel.getName(), "" + sysModel.getId());
                } else {
                    tmp.put(sysModel.getName(), sysModel.getUrl());
                }
                navsTemp.put("" + parentId, tmp);
            } else {
                tmp = navsTemp.get("" + parentId);
                if (parentId == 0) {
                    tmp.put(sysModel.getName(), "" + sysModel.getId());
                } else {
                    tmp.put(sysModel.getName(), sysModel.getUrl());
                }
                navsTemp.put("" + parentId, tmp);
            }

        }

        Map<String, Map<String, String>> navs = new HashMap<>();
        Map<String, String> firstNavs = navsTemp.get("0");
        for(String id:firstNavs.keySet()){
            navs.put(id,navsTemp.get(firstNavs.get(id)));
        }

        return navs;
    }
}
