package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.AlgorithmDao;
import com.dreamy.domain.ipcool.Algorithm;
import com.dreamy.domain.ipcool.AlgorithmConditions;
import com.dreamy.service.iface.ipcool.AlgorithmService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/19.
 */
@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    @Resource
    private AlgorithmDao algorithmDao;
    @Override
    public List<Algorithm> getList(Algorithm algorithm, Page page) {
        Map<String,Object> params= BeanUtils.toQueryMap(algorithm);
        AlgorithmConditions conditions=new AlgorithmConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null){
            page.setTotalNum(algorithmDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return algorithmDao.selectByExample(conditions);
    }

    @Override
    public void save(Algorithm algorithm) {
        algorithmDao.save(algorithm);

    }

    @Override
    public Algorithm getById(Integer id) {
        return algorithmDao.selectById(id);
    }
}
