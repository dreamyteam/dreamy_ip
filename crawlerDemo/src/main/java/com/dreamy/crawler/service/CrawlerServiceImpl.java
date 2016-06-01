package com.dreamy.crawler.service;

import com.dreamy.domain.ipcool.*;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.*;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.asynchronous.AsynchronousService;
import com.dreamy.utils.asynchronous.ObjectCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    private RedisClientService redisClientService;


    @Value("${queue_crawler_over}")
    private String queueName;


    @Autowired
    private IpBookService ipBookService;

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    BookScoreService bookScoreService;
    @Autowired
    NetBookInfoService netBookInfoService;

    @Autowired
    private BookTagsService bookTagsService;
    @Autowired
    private BookViewService bookViewService;

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
    public void Operation(String operation, String key, final BookInfo bookInfo, String title, Integer bookId, String url, String isbn, final Integer type) {
        try {
            if (bookInfo != null) {
                if (operation.equals(OperationEnums.crawler.getCode())) {
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
                    bookCrawlerInfo.setSource(type);
                    bookCrawlerInfo.url(url);
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                    if (StringUtils.isNotEmpty(bookInfo.getISBN())) {
                        pushAll(bookInfo.getISBN(), url, ipBook.getId());
                    }
                    bookInfo.setId(bookInfo.getISBN() + "_" + type);
                } else {
                    bookInfo.setId(isbn + "_" + type);
                }
                score(bookInfo, type, bookId);
                bookInfo.setSource(type);
                bookInfo.setIpId(bookId);
                bookInfoService.updateInser(bookInfo);
            }

        } catch (Exception e) {
            LOGGER.error("Operation is error bookid is " + bookId, e);
        } finally {
            check(key, bookId);
        }

    }


    public void operationBook(String operation, String key, BookInfo bookInfo, Integer bookId, String url, String isbn, Integer type) {
        try {
            if (bookInfo != null) {
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                    bookCrawlerInfo.status(1);
                    bookCrawlerInfo.bookId(bookId);
                    bookCrawlerInfo.setSource(type);
                    bookCrawlerInfo.url(url);
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                    bookInfo.setId(bookInfo.getISBN() + "_" + type);
                } else {
                    bookInfo.setId(isbn + "_" + type);
                }
                score(bookInfo, type, bookId);
                bookInfo.setSource(type);
                bookInfo.setIpId(bookId);
                bookInfoService.updateInser(bookInfo);
            }
        } catch (Exception e) {
            LOGGER.error("operationBook is error bookid is " + bookId, e);
        } finally {
            check(key, bookId);
        }

    }

    @Override
    public void check(String key, int bookId) {
        long num = redisClientService.incrBy(key, -1L);
        if (num < 1) {
            redisClientService.del(key);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bookId", bookId);
            queueService.push(queueName, map);
        }
    }

    @Override
    public void operationNetBook(String operation, String key, NetBookInfo bookInfo, Integer bookId) {
        try {
            if (bookInfo != null) {
                netBookInfoService.updateInser(bookInfo);
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    createTag(bookId, bookInfo.getLabel());
                }
            }
        } catch (Exception e) {
            LOGGER.error("operationNetBook is error bookId is " + bookId, e);
        } finally {
            //check(key, bookId);
        }
    }


    private void createTag(final Integer bookId, final String tags) {

        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                BookView oldBookView = bookViewService.getByBookId(bookId);
                NetBookInfo bookInfo=netBookInfoService.getById(bookId);
                if (oldBookView == null&&bookInfo!=null) {
                    BookView bookView = new BookView();
                    bookView.setName(bookInfo.getTitle());
                    bookView.setImageUrl(bookInfo.getImage());
                    bookView.introduction(bookInfo.getInfo());
                    bookView.setAuthor(bookInfo.getAuthor());
                    bookView.setType(2);
                    bookView.setStatus(0);
                    bookView.bookId(bookId);
                    bookViewService.save(bookView);
                    if (StringUtils.isNotEmpty(tags)) {
                        String arr[] = tags.split(",");
                        int size = arr.length;
                        int tagId = 0;
                        for (int i = 0; i < size; i++) {
                            List<BookTags> bookTagsList = bookTagsService.getByName(arr[i]);

                            if (CollectionUtils.isEmpty(bookTagsList)) {
                                BookTags bookTags = new BookTags();
                                bookTags.name(arr[i]);
                                bookTagsService.save(bookTags);
                                tagId = bookTags.getId();
                            } else {
                                tagId = bookTagsList.get(0).getId();
                            }
                            BookTagsMap bookTagsMap = new BookTagsMap();
                            bookTagsMap.bookId(bookId).tagId(tagId);
                            bookTagsService.saveTagMap(bookTagsMap);
                        }
                    }
                }
                return null;
            }
        });

    }

    private void score(final BookInfo bookInfo, final Integer type, final Integer bookId) {
        AsynchronousService.submit(new ObjectCallable() {
            @Override
            public Object run() throws Exception {
                BookScore bookScore = new BookScore();
                bookScore.source(type);
                bookScore.status(0);
                bookScore.bookId(bookId);
                if (bookInfo.getCommentNum() != null) {
                    bookScore.commentNum(bookInfo.getCommentNum());
                }
                if (bookInfo.getSaleSort() != null) {
                    bookScore.saleSort(Integer.valueOf(bookInfo.getSaleSort()));
                }
                if (bookInfo.getScore() != null) {
                    bookScore.setScore(Double.valueOf(bookInfo.getScore()));
                }
                bookScoreService.saveUpdate(bookScore);
                return null;
            }
        });
    }
}
