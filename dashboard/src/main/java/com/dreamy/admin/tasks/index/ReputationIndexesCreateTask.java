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
 * Date: 16/5/3
 * Time: 下午6:55
 */
@Component
public class ReputationIndexesCreateTask {
    private static final Logger log = LoggerFactory.getLogger(ReputationIndexesCreateTask.class);
    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookIndexTaskLogService bookIndexTaskLogService;

    @Scheduled(fixedDelay = 10000)
    public void run() {
        Integer type = BookIndexTypeEnums.reputation.getType();
        Boolean isTaskActive = bookIndexTaskLogService.isTaskActive(type);
        if (!isTaskActive) {
            return;
        }

        try {
            Page page = new Page();
            page.setPageSize(1000);
            List<BookView> bookViews = bookViewService.getListByPageAndOrder(page, "id asc");
            if (CollectionUtils.isNotEmpty(bookViews)) {
                for (BookView bookView : bookViews) {
                    String reputationIndex = bookScoreService.getReputationIndexByBookId(bookView.getBookId());
                    bookView.reputationIndex(Integer.parseInt(reputationIndex));
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
            log.error("develop indexes create task failed", e);
        }
    }
}
