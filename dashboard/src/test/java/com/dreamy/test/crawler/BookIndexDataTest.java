package com.dreamy.test.crawler;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.OverviewJson;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/5/5.
 */
public class BookIndexDataTest extends BaseJunitTest {
    @Autowired
    private BookIndexDataService bookIndexDataService;

    @Test
    public void find() {
        BookIndexData data = bookIndexDataService.getById("158_2");
        OverviewJson overviewJson= data.getOverviewJson();
        overviewJson.setMonthChainRatio("100");
        data.setLastDate("2017");
        bookIndexDataService.updateInser(data);
         data = bookIndexDataService.getById("158_2");
        System.out.println(data);
    }
}
