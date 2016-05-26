package com.dreamy.crawler.service;

import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ganlv on 11/9/14.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl.class);
    @Autowired
    private QueueService queueService;
//    @Value("${crawler_book_isbn_jd}")
//    private String queueNameJd;
//
//    @Value("${crawler_book_isbn_amazon}")
//    private String queueNameAmazon;
//
//    @Value("${crawler_book_isbn_dangdang}")
//    private String queueNameDangDang;
//
//    @Value("${queue_crawler_comment}")
//    private String commentQueueName;

    @Autowired
    @Qualifier("rawValueOperations")
    private ValueOperations<String, Integer> rawValueOperations;


    @Value("${queue_crawler_over}")
    private String queueName;


    @Autowired
    private IpBookService ipBookService;

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;

    @Override
    public void pushAll(String isbn, String url, Integer bookId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isbn", isbn);
        map.put("url", url);
        map.put("bookId", bookId);
//        queueService.push(queueNameJd, map);
//        queueService.push(queueNameAmazon, map);
//        queueService.push(queueNameDangDang, map);
//        queueService.push(commentQueueName, map);

    }

    @Override
    public void Operation(String operation, String key, BookInfo bookInfo, String title, Integer bookId, String url) {
        try {
            if (bookInfo != null) {
                if (operation.equals("crawler")) {
                    IpBook ipBook = new IpBook();
                    ipBook.setType(1);
                    ipBook.setStatus(1);
                    ipBook.setTitle(title);
                    ipBook.name(title);
                    ipBook.setCode(bookInfo.getISBN());
                    ipBookService.save(ipBook);
                    bookId = ipBook.getId();
                    BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                    bookCrawlerInfo.status(1);
                    bookCrawlerInfo.bookId(ipBook.getId());
                    bookCrawlerInfo.setSource(CrawlerSourceEnums.douban.getType());
                    bookCrawlerInfo.url(url);
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                    if (StringUtils.isNotEmpty(bookInfo.getISBN())) {
                        pushAll(bookInfo.getISBN(), url, ipBook.getId());
                    }
                }
                bookInfo.setSource(CrawlerSourceEnums.douban.getType());
                bookInfo.setIpId(bookId);
                bookInfo.setId(bookInfo.getISBN() + "_" + CrawlerSourceEnums.douban.getType());
                bookInfoService.updateInser(bookInfo);
            }

        } catch (Exception e) {
            LOGGER.error( "Operation is error bookid is "+bookId,e);
        } finally {
            check(key, bookId);
        }

    }


    public void operationBook(String operation, String key, BookInfo bookInfo, Integer bookId, String url) {
        try {
            if (bookInfo != null) {
                if (operation.equals("crawler")) {
                    BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                    bookCrawlerInfo.status(1);
                    bookCrawlerInfo.bookId(bookId);
                    bookCrawlerInfo.setSource(CrawlerSourceEnums.douban.getType());
                    bookCrawlerInfo.url(url);
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                }

                bookInfo.setId(bookInfo.getISBN() + "_" + CrawlerSourceEnums.douban.getType());
                bookInfo.setSource(CrawlerSourceEnums.douban.getType());
                bookInfo.setIpId(bookId);
                bookInfoService.updateInser(bookInfo);
            }
        } catch (Exception e) {
            LOGGER.error( "operationBook is error bookid is "+bookId,e);
        } finally {
            check(key, bookId);
        }

    }

    @Override
    public void check(String key, int bookId) {
        System.out.println(1111);
        long num = rawValueOperations.increment(key, -1);
        if (num <= 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bookId", bookId);
            queueService.push(queueName, map);
        }
    }
}
