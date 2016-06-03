package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.PeopleChart;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/2.
 */
public interface PeopleChartService {

    public void save(PeopleChart peopleChart);

    public List<PeopleChart> getList(PeopleChart peopleChart, Page page);

    public List<PeopleChart> getListByBookId(Integer bookId,Integer type);


}
