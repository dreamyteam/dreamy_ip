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
        Map<String, Double> douban = new HashMap<>();
        douban.put("marketPercent", CrawlerSourceEnums.douban.getPercent());

        Map<String, Double> dangdang = new HashMap<>();
        dangdang.put("marketPercent", CrawlerSourceEnums.dangdang.getPercent());

        Map<String, Double> jd = new HashMap<>();
        jd.put("marketPercent", CrawlerSourceEnums.jd.getPercent());

        Map<String, Double> amazon = new HashMap<>();
        amazon.put("marketPercent", CrawlerSourceEnums.amazon.getPercent());

        options.put(CrawlerSourceEnums.douban.getType(), douban);
        options.put(CrawlerSourceEnums.dangdang.getType(), dangdang);
        options.put(CrawlerSourceEnums.jd.getType(), jd);
        options.put(CrawlerSourceEnums.amazon.getType(), amazon);


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
