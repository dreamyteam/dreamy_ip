package com.dreamy.admin.tasks.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.HotWordService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 上午11:08
 */
@Component
public class RankUpdateTask {

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private HotWordService hotWordService;

    @Autowired
    private IpBookService ipBookService;

    @Autowired
    private QueueService queueService;

    @Value("${queue_amazon_crawler}")
    private String amazonQueue;

    @Value("${queue_jd_crawler}")
    private String jdQueue;

    @Value("${queue_dangdang_crawler}")
    private String dangdangQueue;

    @Value("${queue_douban_crawler}")
    private String doubanQueue;

    @Value("${queue_index_wb}")
    private String wbIndexQueue;

    @Value("${queue_index_360}")
    private String s360IndexQueue;


    @Value("${queue_index_wx}")
    private String wxIndexQueue;

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void run() {
        Page page = new Page();
        page.setPageSize(1);
        page.setTotalNum(bookViewService.getToutleCount());

//        Integer totalPage = page.getTotalPage();
        Integer totalPage = 1;
        Integer currentPage = page.getCurrentPage();
        do {
            List<BookView> bookViewList = bookViewService.getListByPageAndOrder(page, "composite_index desc");
            currentPage++;

            if (CollectionUtils.isNotEmpty(bookViewList)) {
                BookCrawlerInfo record = new BookCrawlerInfo();
                for (BookView bookView : bookViewList) {
                    Integer bookId = bookView.getBookId();
                    record.setBookId(bookView.getBookId());
                    List<BookCrawlerInfo> bookCrawlerInfoList = bookCrawlerInfoService.getListByRecord(record, null);
                    if (CollectionUtils.isNotEmpty(bookCrawlerInfoList)) {

                        Map<Integer, String> salePlatformUrls = new HashMap<>();
                        for (BookCrawlerInfo bookCrawlerInfo : bookCrawlerInfoList) {
                            String url = bookCrawlerInfo.getUrl();
                            if (StringUtils.isNotEmpty(url)) {
                                salePlatformUrls.put(bookCrawlerInfo.getSource(), bookCrawlerInfo.getUrl());
                            }
                        }

                        String flag = "book:update:" + bookId;
                        String action = OperationEnums.update.getCode();
                        IpBook ipBook = ipBookService.getById(bookView.getBookId());
                        if (ipBook != null) {
                            String isbn = ipBook.getCode();

                            try {
                                if (CollectionUtils.isNotEmpty(salePlatformUrls)) {

                                    Long count = redisClientService.getNumber(flag);
                                    if (count == null || count == 0) {
                                        Long stepValue = new Long(1);
                                        redisClientService.setNumber(flag, (long) 0);

                                        if (salePlatformUrls.containsKey(CrawlerSourceEnums.amazon.getType())) {
                                            Map<String, String> params = new HashMap<>();
                                            params.put("bookId", "" + bookId);
                                            params.put("key", flag);
                                            params.put("isbn", isbn);
                                            params.put("operation", action);
                                            params.put("url", salePlatformUrls.get(CrawlerSourceEnums.amazon.getType()));
                                            queueService.push(amazonQueue, params);
                                            redisClientService.incrBy(flag, stepValue);
                                        }

                                        if (salePlatformUrls.containsKey(CrawlerSourceEnums.jd.getType())) {
                                            Map<String, String> params = new HashMap<>();
                                            params.put("bookId", "" + bookId);
                                            params.put("key", flag);
                                            params.put("operation", action);
                                            params.put("isbn", isbn);
                                            params.put("url", salePlatformUrls.get(CrawlerSourceEnums.jd.getType()));
                                            queueService.push(jdQueue, params);
                                            redisClientService.incrBy(flag, stepValue);
                                        }

                                        if (salePlatformUrls.containsKey(CrawlerSourceEnums.dangdang.getType())) {
                                            Map<String, String> params = new HashMap<>();
                                            params.put("bookId", "" + bookId);
                                            params.put("key", flag);
                                            params.put("isbn", isbn);
                                            params.put("operation", action);
                                            params.put("url", salePlatformUrls.get(CrawlerSourceEnums.dangdang.getType()));
                                            queueService.push(dangdangQueue, params);
                                            redisClientService.incrBy(flag, stepValue);
                                        }

                                        if (salePlatformUrls.containsKey(CrawlerSourceEnums.douban.getType())) {
                                            Map<String, String> params = new HashMap<>();
                                            params.put("bookId", "" + bookId);
                                            params.put("key", flag);
                                            params.put("isbn", isbn);
                                            params.put("operation", action);
                                            params.put("url", salePlatformUrls.get(CrawlerSourceEnums.douban.getType()));
                                            queueService.push(doubanQueue, params);
                                            redisClientService.incrBy(flag, stepValue);
                                        }

//                                        HotWord hotWord = hotWordService.getById(bookId);
//                                        if (hotWord != null) {
//                                            Map<String, String> params = new HashMap<>();
//                                            params.put("bookId", "" + bookId);
//                                            params.put("key", flag);
//                                            params.put("operation", action);
//                                            params.put("isbn", isbn);
//                                            params.put("cookie", hotWord.getCookie());
//                                            queueService.push(wbIndexQueue, params);
//                                            redisClientService.incrBy(flag, stepValue);
//                                        }

                                        Map<String, String> params = new HashMap<>();
                                        params.put("bookId", "" + bookId);
                                        params.put("key", flag);
                                        params.put("operation", action);
                                        params.put("name", bookView.getName());
                                        params.put("isbn", isbn);
                                        queueService.push(s360IndexQueue, params);
                                        redisClientService.incrBy(flag, stepValue);

                                        params.put("key", flag);
                                        queueService.push(wxIndexQueue, params);
                                        redisClientService.incrBy(flag, stepValue);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }

        while (currentPage <= totalPage);
    }


}
