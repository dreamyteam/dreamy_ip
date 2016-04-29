package com.dreamy.admin.tasks.index;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookIndexTaskLog;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexStatusEnums;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.service.iface.ipcool.BookIndexTaskLogService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 下午2:17
 */
@Component
public class HotIndexesCreateTask {
    private static final Logger log = LoggerFactory.getLogger(HotIndexesCreateTask.class);
    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookIndexTaskLogService bookIndexTaskLogService;

    private void run() {

    }

    @Scheduled(fixedDelay = 8000)
    public void getHotScore() {
        Integer type = BookIndexTypeEnums.hot.getType();
        Boolean isTaskActive = bookIndexTaskLogService.isTaskActive(type);
        if (!isTaskActive) {
            return;
        }
        Map<Integer, Map<String, Double>> options = new HashMap<>();
        Map<String, Double> map1 = new HashMap<>();
        map1.put("marketPercent", 0.5);
        map1.put("coefficient", 0.1);
        map1.put("argA", 0.1);
        map1.put("argB", 0.1);

        Map<String, Double> map2 = new HashMap<>();
        map2.put("marketPercent", 0.24);
        map2.put("coefficient", 0.01851851852);
        map2.put("argA", 0.1);
        map2.put("argB", 0.1);

        Map<String, Double> map3 = new HashMap<>();
        map3.put("marketPercent", 0.16);
        map3.put("coefficient", 0.1315789474);
        map3.put("argA", 0.1);
        map3.put("argB", 0.1);

        Map<String, Double> map4 = new HashMap<>();
        map4.put("marketPercent", 0.1);
        map4.put("coefficient", 1.4285714286);
        map4.put("argA", 0.1);
        map4.put("argB", 0.1);

        options.put(CrawlerSourceEnums.douban.getType(), map1);
        options.put(CrawlerSourceEnums.dangdang.getType(), map2);
        options.put(CrawlerSourceEnums.jd.getType(), map3);
        options.put(CrawlerSourceEnums.amazon.getType(), map4);


        try {
            Page page = new Page();
            page.setPageSize(1000);
            List<BookView> bookViews = bookViewService.getListByPageAndOrder(page, "id asc");
            if (CollectionUtils.isNotEmpty(bookViews)) {
                for (BookView bookView : bookViews) {
                    String hostIndex = bookScoreService.getBookHotIndexByBookAndOptions(bookView.getBookId(), options);
                    bookView.hotIndex(Integer.parseInt(hostIndex));
                    bookViewService.update(bookView);
                }
            }


            BookIndexTaskLog bookIndexTaskLog = bookIndexTaskLogService.getByIndexType(type);
            if (bookIndexTaskLog.getId() != null) {
                Integer oldRunTime = bookIndexTaskLog.getRunTime();
                bookIndexTaskLog.status(BookIndexStatusEnums.finished.getStatus()).runTime(oldRunTime + 1);
                bookIndexTaskLogService.update(bookIndexTaskLog);
            }

        } catch (NumberFormatException e) {
            log.error("hot indexes create task failed", e);
        }
    }
}
