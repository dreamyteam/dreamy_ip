package com.dreamy.dao.impl.admin;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.admin.SysModelDao;
import com.dreamy.domain.admin.SysModel;
import com.dreamy.domain.admin.SysModelConditions;
import com.dreamy.mapper.admin.SysModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangyongxing on 16/3/31.
 */
@SuppressWarnings("unchecked")
@Repository("sysModelDao")
public class SysModelDaoImpl extends BaseDaoImpl<SysModel,Integer,SysModelConditions> implements SysModelDao {

    @Autowired
    private SysModelMapper sysModelMapper;

    //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(sysModelMapper);
    }


    @Override
    public List<SysModel> selectByRoles(List<Integer> roles) {
        return sysModelMapper.selectByRoles(roles);
    }

    @Override
    public List<SysModel> getByUserId(Integer userId) {
        return null;
    }
}
