package com.dreamy.test.crawler;

import com.dreamy.admin.service.SinaLoginService;
import com.dreamy.admin.tasks.KeyWorkTask;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.enums.QueueRoutingKeyEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/20.
 */
public class CawlerTest extends BaseJunitTest {

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    QueueService queueService;
    @Autowired
    KeyWorkTask keyWorkTask;

    @Autowired
    private SinaLoginService sinaLoginService;

    @Autowired
    private BookViewService bookViewService;


    @Autowired
    BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private BookScoreService bookScoreService;


    @Value("${queue_crawler_qidian}")
    private String queueName;

    @Test
    public void test() {
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(new BookCrawlerInfo().source(CrawlerSourceEnums.qidian.getType()), null);

        for (BookCrawlerInfo info : list) {
            Map<String, Object> map = new HashMap<>();
            if (StringUtils.isNotEmpty(info.getUrl())) {
                map.put("url", info.getUrl());
                map.put("bookId", info.getBookId());
                map.put("operation", OperationEnums.crawler.getCode());
                queueService.push(queueName, map);
            }
        }
    }

    @Test
    public void crawlerWeiXin() {
        sinaLoginService.init();
        keyWorkTask.crawlerWeiBo();
    }


    @Test
    public void tt() {
        BookView bookView = bookViewService.getByBookId(516);
        String tt = bookScoreService.getDevelopIndexByRecord(bookView);
        System.err.println("111");
    }


}
