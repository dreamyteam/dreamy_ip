package com.dreamy.service.impl.sys;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.sys.SysOptionDao;
import com.dreamy.domain.sys.SysOption;
import com.dreamy.domain.sys.SysOptionConditions;
import com.dreamy.service.iface.sys.SysOptionService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/4.
 */
@Service
public class SysOptionServiceImpl implements SysOptionService {
    @Resource
    private SysOptionDao sysOptionDao;
    @Override
    public void save(SysOption sysOption) {
        sysOptionDao.save(sysOption);
    }

    @Override
    public List<SysOption> getList(SysOption sysOption, Page page) {
        Map<String,Object> params= BeanUtils.toQueryMap(sysOption);
        SysOptionConditions conditions=new SysOptionConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null){
            page.setTotalNum(sysOptionDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return sysOptionDao.selectByExample(conditions);
    }

    @Override
    public SysOption getById(Integer id) {

        return sysOptionDao.selectById(id);
    }

    @Override
    public SysOption getByCode(String code) {
        SysOptionConditions conditions=new SysOptionConditions();
        conditions.createCriteria().andCodeEqualTo(code);
        List<SysOption> list=sysOptionDao.selectByExample(conditions);
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer update(SysOption sysOption) {
        return sysOptionDao.update(sysOption);
    }
}
