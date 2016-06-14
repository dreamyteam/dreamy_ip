package com.dreamy.test.crawler;

import com.dreamy.admin.tasks.BookViewCreateTask;
import com.dreamy.admin.thread.ExtractBookViewService;
import com.dreamy.admin.thread.ExtractThread;
import com.dreamy.admin.thread.ExtractThreadManager;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.HotWordDao;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/27
 * Time: 上午10:28
 */
@Component
public class BookViewCreateTaskTest extends BaseJunitTest {
    @Autowired
    BookViewCreateTask bookViewCreateTask;

    @Autowired
    private IpBookService ipBookService;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private ExtractBookViewService extractBookViewService;
    @Autowired
    private BookInfoDao bookInfoDao;

    @Value("${queue_douban_comment}")
    private String commentQueueName;
    @Autowired
    QueueService queueService;
    @Autowired
    HotWordDao hotWordDao;

    @Test
    public void create() {
        int current = 1;
        IpBook ipBook = new IpBook();
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(current);
            List<IpBook> list = ipBookService.getIpBookList(ipBook, page);
            ExtractThread extractThread = new ExtractThread(extractBookViewService, list);
            ExtractThreadManager.run(extractThread);
            if (!page.isHasNextPage()) {
                try {
                    Thread.sleep(800 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            current++;

        }

    }

    @Test
    public void comment() {
        int current = 1;
        BookCrawlerInfo entity = new BookCrawlerInfo().source(4);
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(current);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);
            for (BookCrawlerInfo info : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("isbn", "");
                map.put("url", info.getUrl());
                map.put("bookId", info.getBookId());
                queueService.push(commentQueueName, map);
            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;

        }

    }



    @Test
    public void update() {
        int current = 1;
        IpBook ipBook = new IpBook();
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(current);
            List<IpBook> list = ipBookService.getIpBookList(ipBook, page);

            for(IpBook book:list){
                BookInfo bookInfo=new BookInfo();
                bookInfo.setIpId(book.getId());
                bookInfo.setISBN(book.getCode());
                bookInfoDao.updateMulti(bookInfo);
            }
            current++;

        }

    }

}
