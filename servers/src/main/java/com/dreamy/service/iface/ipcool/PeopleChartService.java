package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.PeopleChart;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/2.
 */
public interface PeopleChartService {

    /**
     * @param peopleChart
     */
    public void save(PeopleChart peopleChart);

    /**
     * @param peopleChart
     * @param page
     * @return
     */
    public List<PeopleChart> getList(PeopleChart peopleChart, Page page);

    /**
     * @param bookId
     * @return
     */
    public List<PeopleChart> getListByBookId(Integer bookId);

    public void saveOrUpdate(PeopleChart peopleChart);


}
