package com.dreamy.admin.tasks.rank;

import com.dreamy.beans.Page;
import com.dreamy.beans.params.BookCrawlerParams;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.enums.BookTypeEnums;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
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

    @Scheduled(fixedDelay = 1000 * 6)
    public void run() {
        Page page = new Page();
        page.setPageSize(200);
        page.setTotalNum(bookViewService.getToutleCount());

        Integer totalPage = page.getTotalPage();
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

                        String flag = "wwxss:" + bookId;
                        if (CollectionUtils.isNotEmpty(salePlatformUrls)) {
                            Integer count = redisClientService.get(flag);
                            if (count == null || count == 0) {
                                redisClientService.set(flag, 0);

                                if (salePlatformUrls.containsKey(CrawlerSourceEnums.amazon.getType())) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", "" + bookId);
                                    params.put("flag", flag);
                                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.amazon.getType()));
                                    queueService.push(amazonQueue, params);
                                    redisClientService.incrBy(flag, (long) 1);
                                }

                                if (salePlatformUrls.containsKey(CrawlerSourceEnums.jd.getType())) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", "" + bookId);
                                    params.put("flag", flag);
                                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.jd.getType()));
                                    queueService.push(jdQueue, params);
                                    redisClientService.incrBy(flag, (long) 1);
                                }

                                if (salePlatformUrls.containsKey(CrawlerSourceEnums.dangdang.getType())) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", "" + bookId);
                                    params.put("flag", flag);
                                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.dangdang.getType()));
                                    queueService.push(dangdangQueue, params);
                                    redisClientService.incrBy(flag, (long) 1);
                                }

                                if (salePlatformUrls.containsKey(CrawlerSourceEnums.douban.getType())) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", "" + bookId);
                                    params.put("flag", flag);
                                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.douban.getType()));
                                    queueService.push(doubanQueue, params);
                                    redisClientService.incrBy(flag, (long) 1);
                                }

                                HotWord hotWord = hotWordService.getById(bookId);
                                if (hotWord != null) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", "" + bookId);
                                    params.put("flag", flag);
                                    params.put("cookie", hotWord.getCookie());
                                    queueService.push(doubanQueue, params);
                                    redisClientService.incrBy(flag, (long) 1);
                                }

                                Map<String, String> params = new HashMap<>();
                                params.put("id", "" + bookId);
                                params.put("flag", flag);
                                params.put("name", bookView.getName());
                                queueService.push(s360IndexQueue, params);
                                redisClientService.incrBy(flag, (long) 1);

                                params.put("flag", flag);
                                queueService.push(wxIndexQueue, params);
                                redisClientService.incrBy(flag, (long) 1);
                            }
                        }

                    }
                }
            }
        }

        while (currentPage <= totalPage);
    }


}
