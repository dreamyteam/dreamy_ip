package com.dreamy.service.iface.admin;

import com.dreamy.beans.Page;
import com.dreamy.domain.admin.SysModel;

import java.util.*;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface
        SysModelService {


    public List<SysModel> getSysModelPage(SysModel sysModel, Page page);

    public List<SysModel> queryByRoles(List<Integer> roles);

    public List<SysModel> getByUserId(Integer userId);

    public List<SysModel> getAll();


    public Map<Integer, Object[]>  getSysModelMapByUserId(int userId);




}
