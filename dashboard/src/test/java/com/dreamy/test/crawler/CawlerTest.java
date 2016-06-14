package com.dreamy.test.crawler;

import com.dreamy.admin.service.SinaLoginService;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.service.iface.ipcool.*;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
    private SinaLoginService sinaLoginService;

    @Autowired
    private BookViewService bookViewService;
    @Autowired
    private IpBookService ipBookService;


    @Autowired
    BookIndexHistoryService bookIndexHistoryService;

    @Autowired
    private BookScoreService bookScoreService;
    @Autowired
    private NetBookInfoService netBookInfoService;


    @Value("${queue_amazon_crawler}")
    private String queueName;

    @Value("${queue_crawler_qidian_fans}")
    private String queueFanName;

    @Value("${queue_crawler_tieba}")
    private String queueTieBa;


    @Test
    public void test() {
        BookCrawlerInfo entity = new BookCrawlerInfo().source(CrawlerSourceEnums.amazon.getType());
        Page page = new Page();
        page.setPageSize(200);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);

            for (BookCrawlerInfo info : list) {
                Map<String, Object> map = new HashMap<>();
                if (StringUtils.isNotEmpty(info.getUrl())) {
                    map.put("url", info.getUrl());
                    map.put("bookId", info.getBookId());
                    map.put("operation", OperationEnums.update.getCode());
                    queueService.push(queueName, map);
                }
            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }

    @Test
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

    @Test
    public void crawlerWeiXin() {
        sinaLoginService.init();
    }


    @Test
    public void tt() {
        BookView bookView = bookViewService.getByBookId(516);
        String tt = bookScoreService.getDevelopIndexByRecord(bookView);
        System.err.println("111");
    }

    @Test
    public void isbn() {
        IpBook entity = new IpBook();
        entity.setType(IpTypeEnums.chuban.getType());
        entity.setStatus(3);
        Page page = new Page();
        page.setPageSize(200);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<IpBook> list = ipBookService.getIpBookList(entity, page);

            for (IpBook info : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("bookId", info.getId());
                map.put("isbn", info.getCode());
                map.put("operation", OperationEnums.crawler.getCode());
                queueService.push(queueName, map);
            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }

}
