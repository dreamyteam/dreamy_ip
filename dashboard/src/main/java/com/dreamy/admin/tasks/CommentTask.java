package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.mq.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/4.
 */
@Component
public class CommentTask {

    @Value("${queue_douban_comment}")
    private String commentQueue;

    @Autowired
    private QueueService queueService;


    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Scheduled(cron = "0 42 09 * * ?")
    public void run() {
        BookCrawlerInfo entity = new BookCrawlerInfo().source(CrawlerSourceEnums.douban.getType());
        Page page = new Page();
        page.setPageSize(200);
        int currentPage = 1;
        while (true) {
            page.setCurrentPage(currentPage);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);
            for (BookCrawlerInfo crawlerInfo : list) {
                Map<String, Object> params = new HashMap<>();
                params.put("bookId", crawlerInfo.getBookId());
                params.put("url", crawlerInfo.getUrl());
                queueService.push(commentQueue, params);
            }
            if (!page.isHasNextPage()) {
                break;
            }
            currentPage++;
        }
    }
}
