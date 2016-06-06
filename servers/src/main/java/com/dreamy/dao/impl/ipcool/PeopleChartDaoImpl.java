package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.PeopleChartDao;
import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.domain.ipcool.PeopleChartConditions;
import com.dreamy.mapper.ipcool.PeopleChartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyongxing on 16/6/2.
 */
@Repository("peopleChartDao")
public class PeopleChartDaoImpl  extends BaseDaoImpl<PeopleChart,Integer,PeopleChartConditions> implements PeopleChartDao {
    @Autowired
    PeopleChartMapper peopleChartMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        setBaseMapper(peopleChartMapper);
    }
}
