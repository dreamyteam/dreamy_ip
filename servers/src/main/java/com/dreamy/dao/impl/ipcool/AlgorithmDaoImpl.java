package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.AlgorithmDao;
import com.dreamy.domain.ipcool.Algorithm;
import com.dreamy.domain.ipcool.AlgorithmConditions;
import com.dreamy.mapper.ipcool.AlgorithmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/19.
 */
@Repository("algorithmDao")
public class AlgorithmDaoImpl extends BaseDaoImpl<Algorithm,Integer,AlgorithmConditions> implements AlgorithmDao {
    @Resource
    private AlgorithmMapper algorithmMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(algorithmMapper);
    }
}
