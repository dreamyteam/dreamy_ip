package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/16.
 */
@Component
public class QiDianTask {

    @Autowired
    private QueueService queueService;
    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    private NetBookInfoService netBookInfoService;

    @Value("${queue_crawler_qidian_fans}")
    private String queueFanName;

    /**
     * 起点粉丝
     */
    @Scheduled(cron = "0 10 02 * * ?")
    public void fans() {
        BookCrawlerInfo entity = new BookCrawlerInfo().source(CrawlerSourceEnums.qidian.getType());
        Page page = new Page();
        page.setPageSize(200);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);
            for (BookCrawlerInfo info : list) {
                NetBookInfo netBookInfo = netBookInfoService.getById(info.getBookId());
                Map<String, Object> map = new HashMap<>();
                if (netBookInfo != null && StringUtils.isNotEmpty(netBookInfo.getAuthorUrl())) {
                    map.put("url", netBookInfo.getAuthorUrl());
                    map.put("bookId", info.getBookId());
                    queueService.push(queueFanName, map);
                }
            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }


    /**
     * 起点粉丝
     */
    @Scheduled(cron = "0 10 02 * * ?")
    public void qianMmfans() {
        BookCrawlerInfo entity = new BookCrawlerInfo().source(CrawlerSourceEnums.qidianmm.getType());
        Page page = new Page();
        page.setPageSize(200);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);
            for (BookCrawlerInfo info : list) {
                NetBookInfo netBookInfo = netBookInfoService.getById(info.getBookId());

                if (netBookInfo != null && StringUtils.isNotEmpty(netBookInfo.getAuthorUrl())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("url", netBookInfo.getAuthorUrl());
                    map.put("bookId", info.getBookId());
                    queueService.push(queueFanName, map);
                }
            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }
}
