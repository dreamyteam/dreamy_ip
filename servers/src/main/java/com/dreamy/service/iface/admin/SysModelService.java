package com.dreamy.service.iface.admin;

import com.dreamy.beans.Page;
import com.dreamy.domain.admin.SysModel;

import java.util.*;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface SysModelService {

    /**
     * @param sysModel
     * @param page
     * @return
     */
    public List<SysModel> getSysModelPage(SysModel sysModel, Page page);

    /**
     * @param roles
     * @return
     */
    public List<SysModel> queryByRoles(List<Integer> roles);

    /**
     * @param userId
     * @return
     */
    public List<SysModel> getByUserId(Integer userId);

    /**
     * @return
     */
    public List<SysModel> getAll();


    /**
     * @param userId
     * @return
     */
    public Map<Integer, Object[]> getSysModelMapByUserId(int userId);


}
