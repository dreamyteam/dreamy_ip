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
import com.dreamy.service.iface.ipcool.RankService;
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
    private RankService rankService;

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


    @Value("${queue_keyword_wx}")
    private String wxKeyWordQueue;

    @Value("${queue_keyword_wb}")
    private String wbKeyWordQueue;

    @Value("${queue_keyword_baidu_sougou}")
    private String bsKeyWordQueue;

    @Value("${queue_news_sougou}")
    private String newsSougouQueue;



    private Long setValue = 1L;


    @Scheduled(fixedDelay = 1000 * 60*60)
    public void run() {
        Page page = new Page();
        page.setPageSize(100);
        page.setTotalNum(bookViewService.getToutleCount());

        Integer totalPage = page.getTotalPage();
        Integer currentPage = page.getCurrentPage();
        do {
            List<BookView> bookViewList = bookViewService.getListByPageAndOrder(page, "composite_index desc");
            currentPage++;

            if (CollectionUtils.isNotEmpty(bookViewList)) {

                for (BookView bookView : bookViewList) {
                    BookCrawlerInfo record = new BookCrawlerInfo().bookId(bookView.getBookId());
                    List<BookCrawlerInfo> bookCrawlerInfoList = bookCrawlerInfoService.getListByRecord(record, null);
                    if (CollectionUtils.isEmpty(bookCrawlerInfoList)) {
                        break;
                    }

                    Map<Integer, String> salePlatformUrls = new HashMap<>();
                    for (BookCrawlerInfo bookCrawlerInfo : bookCrawlerInfoList) {
                        String url = bookCrawlerInfo.getUrl();
                        if (StringUtils.isNotEmpty(url)) {
                            salePlatformUrls.put(bookCrawlerInfo.getSource(), bookCrawlerInfo.getUrl());
                        }
                    }

                    if (CollectionUtils.isEmpty(salePlatformUrls)) {
                        break;
                    }

                    Map<String, String> commonParams = rankService.getCommonParamsByBookIdAndAction(bookView.getBookId(), OperationEnums.update.getCode());
                    if (CollectionUtils.isEmpty(commonParams)) {
                        break;
                    }

                    try {
                        if (CollectionUtils.isEmpty(salePlatformUrls)) {
                            break;
                        }

                        Long count = redisClientService.getNumber(commonParams.get("key"));
                        if (count == null || count == 0) {
                            redisClientService.setNumber(commonParams.get("key"), (long) 0);

                            if (salePlatformUrls.containsKey(CrawlerSourceEnums.amazon.getType())) {
                                Map<String, String> params = commonParams;
                                params.put("url", salePlatformUrls.get(CrawlerSourceEnums.amazon.getType()));
                                pushToQueue(amazonQueue, params);
                            }

                            if (salePlatformUrls.containsKey(CrawlerSourceEnums.jd.getType())) {
                                Map<String, String> params = commonParams;
                                params.put("url", salePlatformUrls.get(CrawlerSourceEnums.jd.getType()));
                                pushToQueue(jdQueue, params);
                            }

                            if (salePlatformUrls.containsKey(CrawlerSourceEnums.dangdang.getType())) {
                                Map<String, String> params = commonParams;
                                params.put("url", salePlatformUrls.get(CrawlerSourceEnums.dangdang.getType()));
                                pushToQueue(dangdangQueue, params);
                            }

                            if (salePlatformUrls.containsKey(CrawlerSourceEnums.douban.getType())) {
                                Map<String, String> params = commonParams;
                                params.put("url", salePlatformUrls.get(CrawlerSourceEnums.douban.getType()));
                                pushToQueue(doubanQueue, params);
                            }

                            Map<String, String> params = commonParams;

                            params.put("name", bookView.getName());
                            pushToQueue(s360IndexQueue, params);
                            pushToQueue(wbIndexQueue, params);
                            pushToQueue(wbKeyWordQueue, params);
                            pushToQueue(wxKeyWordQueue, params);
                            pushToQueue(bsKeyWordQueue, params);
                            pushToQueue(newsSougouQueue, params);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        while (currentPage <= totalPage);
    }

    public void pushToQueue(String queueName, Map<String, String> params) {
        queueService.push(queueName, params);
        redisClientService.incrBy(params.get("key"), setValue);
    }


}
