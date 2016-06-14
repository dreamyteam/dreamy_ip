package com.dreamy.test.crawler;

import com.dreamy.admin.tasks.BookIndexHistoryTask;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.OverviewJson;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.PeopleChartService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/5.
 */
public class BookIndexDataTest extends BaseJunitTest {
    @Autowired
    private BookIndexDataService bookIndexDataService;
    @Autowired
    BookViewService bookViewService;
    @Autowired
    PeopleChartService peopleChartService;
    @Autowired
    BookIndexHistoryTask bookIndexHistoryTask;

    @Test
    public void find() {
        bookIndexHistoryTask.copy();
//        BookIndexData data = bookIndexDataService.getById("158_2");
//        OverviewJson overviewJson = data.getOverviewJson();
//        overviewJson.setMonthChainRatio("100");
//        data.setLastDate("2017");
//        bookIndexDataService.updateInser(data);
//        data = bookIndexDataService.getById("158_2");
//        System.out.println(data);
    }


}

