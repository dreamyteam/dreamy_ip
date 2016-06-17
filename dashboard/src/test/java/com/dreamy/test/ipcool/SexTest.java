package com.dreamy.test.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.qidian.FanInfo;
import com.dreamy.mogodb.beans.qidian.QiDianFan;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.PeopleChartService;
import com.dreamy.service.iface.mongo.QiDianFanService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.NumberUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class SexTest extends BaseJunitTest {
    @Autowired
    BookIndexHistoryService bookIndexHistoryService;


    @Autowired
    private QiDianFanService qiDianFanService;


    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    PeopleChartService peopleChartService;

    @Test
    public void sex() {
        BookCrawlerInfo entity = new BookCrawlerInfo().source(CrawlerSourceEnums.qidian.getType());
        Page page = new Page();
        page.setPageSize(500);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);
            for (BookCrawlerInfo info : list) {
                QiDianFan qiDianFan = qiDianFanService.getById(info.getBookId());
                if (qiDianFan != null) {
                    calculate(qiDianFan.getList(), info.getBookId());
                } else {
                    calculate(null, info.getBookId());
                }

            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }

    private void calculate(List<FanInfo> list, Integer bookId) {
        int i = 0;
        double female = 0.0;
        double male = 0.0;
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            for (FanInfo fanInfo : list) {
                if (fanInfo.getSex().equals("男")) {
                    i++;
                }
            }
            male = NumberUtils.div(Double.valueOf(i), Double.valueOf(size), 4);
            female = 1.0 - male;
        }
        PeopleChart peopleChart = new PeopleChart();
        peopleChart.bookId(bookId);
        peopleChart.male(male);
        peopleChart.female(female);
        peopleChartService.save(peopleChart);


    }


}
