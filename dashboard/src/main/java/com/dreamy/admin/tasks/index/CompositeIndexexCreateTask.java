package com.dreamy.admin.tasks.index;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookIndexTaskLog;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexStatusEnums;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.service.iface.ipcool.BookIndexTaskLogService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午2:12
 */
@Component
public class CompositeIndexexCreateTask {
    private static final Logger log = LoggerFactory.getLogger(CompositeIndexexCreateTask.class);
    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookIndexTaskLogService bookIndexTaskLogService;

//    @Scheduled(fixedDelay = 1000 * 8)
    public void run() {
        Integer type = BookIndexTypeEnums.composite.getType();
        Boolean isTaskActive = bookIndexTaskLogService.isTaskActive(type);
        if (!isTaskActive) {
            return;
        }
        try {
            Page page = new Page();
            page.setPageSize(200);
            int current = 1;
            while (true) {
                page.setCurrentPage(current);
                List<BookView> bookViews = bookViewService.getListByPageAndOrder(page, "id asc");
                if (CollectionUtils.isNotEmpty(bookViews)) {
                    for (BookView bookView : bookViews) {
                        Integer hotIndex = bookView.getHotIndex();
                        Integer propatationIndex = bookView.getPropagateIndex();
                        Integer reputationIndex = bookView.getReputationIndex();
                        Integer developIndex = bookView.getDevelopIndex();

                        Double compositeIndex = 0.1 * (3 * (hotIndex + propatationIndex) + 2 * (reputationIndex + developIndex));

                        bookView.compositeIndex(compositeIndex.intValue());
                        bookViewService.update(bookView);
                    }
                    BookIndexTaskLog bookIndexTaskLog = bookIndexTaskLogService.getByIndexType(type);
                    if (bookIndexTaskLog.getId() != null) {
                        Integer oldRunTime = bookIndexTaskLog.getRunTime();
                        bookIndexTaskLog.status(BookIndexStatusEnums.finished.getStatus()).runTime(oldRunTime + 1);
                        bookIndexTaskLogService.update(bookIndexTaskLog);
                    }
                    if (!page.isHasNextPage()) {
                        break;
                    }
                    current++;
                }

            }


        } catch (NumberFormatException e) {
            log.error("composite indexes create task failed", e);
        }
    }
}
