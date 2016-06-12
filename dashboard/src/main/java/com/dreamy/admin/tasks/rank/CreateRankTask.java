package com.dreamy.admin.tasks.rank;

import com.dreamy.admin.handler.CrawlerFinishQueueHandler;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 上午11:08
 */
@Component
public class CreateRankTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateRankTask.class);

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private CrawlerFinishQueueHandler crawlerFinishQueueHandler;


    @Scheduled(cron = "0 30 00 * * ?")
//    @Scheduled(fixedDelay = 100000)
    public void run() {
        LOGGER.info("start update rank job.." + TimeUtils.toString("yyyy-MM-dd HH:mm:ss", new Date()));

        int currentPage = 1;
        Page page = new Page();
        page.setPageSize(300);
        Boolean isLoop = true;

        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.chuban.getType());
                if (CollectionUtils.isNotEmpty(bookViewList)) {
                    for (BookView bookView : bookViewList) {
                        crawlerFinishQueueHandler.updateRank(bookView);
                    }
                    currentPage++;
                } else {
                    isLoop = false;
                }

            } catch (Exception e) {
                LOGGER.error("update index jod error ", e);
                break;
            }
        }


    }


}
