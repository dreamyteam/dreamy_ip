package com.dreamy.test.crawler;

import com.dreamy.admin.service.SinaLoginService;
import com.dreamy.admin.tasks.KeyWorkTask;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.QueueRoutingKeyEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    BookIndexHistoryService bookIndexHistoryService;

    @Test
    public void test() {
        List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(new BookCrawlerInfo(), null);

        for (BookCrawlerInfo info : list) {
            Map<String, Object> map = new HashMap<>();
            if (StringUtils.isNotEmpty(info.getUrl())) {
                map.put("type", info.getSource());
                map.put("url", info.getUrl());
                map.put("ipId", info.getBookId());
                map.put("crawlerId", info.getId());
                queueService.push(QueueRoutingKeyEnums.publish_book.getKey(), map);
                if (info.getSource().equals(CrawlerSourceEnums.douban.getType())) {
                    queueService.push(QueueRoutingKeyEnums.publish_book_comment.getKey(), map);
                }
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
        int id=6015;
       int aa= bookIndexHistoryService.delByBookIdAndDate(id,new Date());
        System.out.println(aa);
        //updateRankAndIndexTask.run();
    }


}
