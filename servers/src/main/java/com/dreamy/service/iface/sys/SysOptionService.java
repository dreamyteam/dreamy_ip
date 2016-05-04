package com.dreamy.service.iface.sys;

import com.dreamy.beans.Page;
import com.dreamy.domain.sys.SysOption;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/4.
 */
public interface SysOptionService {
    public void save(SysOption sysOption);

    public List<SysOption> getList(SysOption sysOption, Page page);

    public SysOption getById(Integer id);

    public SysOption getByCode(String code);

    public  Integer update(SysOption sysOption);
}
