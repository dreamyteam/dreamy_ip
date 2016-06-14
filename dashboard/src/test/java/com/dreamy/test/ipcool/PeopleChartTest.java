package com.dreamy.test.ipcool;

import com.dreamy.admin.tasks.HuaYuTicketTask;
import com.dreamy.domain.ipcool.PeopleChart;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.PeopleChartService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class PeopleChartTest extends BaseJunitTest {

    @Autowired
    private BookIndexDataService bookIndexDataService;
    @Autowired
    BookViewService bookViewService;
    @Autowired
    PeopleChartService peopleChartService;
    @Autowired
    HuaYuTicketTask huaYuTicketTask;

    @Test
    public void save() {
        huaYuTicketTask.run();
//        Page page = new Page();
//        page.setPageSize(7000);
//        page.setCurrentPage(1);
//        List<BookView> views = bookViewService.getList(new BookView().type(1), page, null);
//
//        for (BookView bookView : views) {
//            List<BookIndexData> list = bookIndexDataService.getByBookId(bookView.getBookId());
//            double female = 0.0;
//            double male = 0.0;
//            int i = 1;
//            for (BookIndexData data : list) {
//                i = list.size();
//                if (StringUtils.isNotEmpty(data.getFemale())) {
//                    female += Double.valueOf(data.getFemale());
//                }
//                if (StringUtils.isNotEmpty(data.getMale())) {
//                    male += Double.valueOf(data.getMale());
//                }
//
//            }
//            PeopleChart peopleChart = new PeopleChart();
//            peopleChart.setBookId(bookView.getBookId());
//            peopleChart.setMale(male / i);
//            peopleChart.setFemale(female / i);
//            age(i, list, peopleChart);
//
//        }


    }

    public void age(int num, List<BookIndexData> list, PeopleChart peopleChart) {
        double a1 = 0.0;
        double a2 = 0.0;
        double a3 = 0.0;
        double a4 = 0.0;
        double a5 = 0.0;

        for (BookIndexData data : list) {
            String arr[] = data.getAge();
            if (arr != null) {
                if (data.getSource().equals(IndexSourceEnums.weibo.getType())) {
                    double[] cc = cc(arr);
                    a1 += cc[0];
                    a2 += cc[1];
                    a3 += cc[2];
                    a4 += cc[3];
                    a5 += cc[4];
                } else {
                    a1 += Double.valueOf(arr[0]);
                    a2 += Double.valueOf(arr[1]);
                    a3 += Double.valueOf(arr[2]);
                    a4 += Double.valueOf(arr[3]);
                    a5 += Double.valueOf(arr[4]);
                }

            }
        }
        peopleChart.setAgeFirst(a1 / num);
        peopleChart.setAgeScond(a2 / num);
        peopleChart.setAgeThird(a3 / num);
        peopleChart.setAgeFourth(a4 / num);
        peopleChart.setAgeFifth(a5 / num);
        peopleChartService.saveOrUpdate(peopleChart);


    }


    private double[] cc(String ages[]) {
        double totalPeople = 0;
        for (String people : ages) {
            totalPeople += Double.parseDouble(people);
        }
        if (totalPeople < 1) {
            totalPeople = 1;
        }
        double arr[] = new double[5];

        arr[0] = Double.parseDouble(ages[0]) / totalPeople;
        arr[1] = Double.parseDouble(ages[1]) / totalPeople;
        arr[2] = Double.parseDouble(ages[2]) / totalPeople;
        arr[3] = Double.parseDouble(ages[3]) / totalPeople;
        arr[4] = Double.parseDouble(ages[4]) / totalPeople;


        return arr;
    }

}
