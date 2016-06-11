package com.dreamy.admin.tasks.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.RankService;
import com.dreamy.service.iface.mongo.HotWordService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
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
public class UpdateIndexTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateIndexTask.class);

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    private RankService rankService;

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


    @Value("${queue_keyword_wx}")
    private String wxKeyWordQueue;

    @Value("${queue_keyword_wb}")
    private String wbKeyWordQueue;

    @Value("${queue_keyword_baidu_sougou}")
    private String bsKeyWordQueue;

    @Value("${queue_news_sougou}")
    private String newsSougouQueue;


    @Scheduled(cron = "0 30 2 * * ?")
    public void run() {
        LOGGER.info("start update rank job.." + TimeUtils.toString("yyyy-MM-dd HH:mm:ss", new Date()));

        redisClientService.del(BookRankEnums.composite.getCacheKey());
        redisClientService.del(BookRankEnums.develop.getCacheKey());
        redisClientService.del(BookRankEnums.propagation.getCacheKey());
        redisClientService.del(BookRankEnums.hot.getCacheKey());

        int currentPage = 1;
        Page page = new Page();
        page.setPageSize(100);
        Boolean isLoop = true;

        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.chuban.getType());
                if (CollectionUtils.isNotEmpty(bookViewList)) {
                    for (BookView bookView : bookViewList) {
                        updateByBookView(bookView);
                    }
                    currentPage++;
                } else {
                    isLoop = false;
                }

            } catch (Exception e) {
                LOGGER.error("update index jod error ", e);
                break;
            }
        }
    }

    public void updateByBookView(BookView bookView) {
        BookCrawlerInfo record = new BookCrawlerInfo().bookId(bookView.getBookId());
        List<BookCrawlerInfo> bookCrawlerInfoList = bookCrawlerInfoService.getListByRecord(record, null);
        if (CollectionUtils.isEmpty(bookCrawlerInfoList)) {
            return;
        }

        Map<Integer, String> salePlatformUrls = new HashMap<>();
        for (BookCrawlerInfo bookCrawlerInfo : bookCrawlerInfoList) {
            String url = bookCrawlerInfo.getUrl();
            if (StringUtils.isNotEmpty(url)) {
                salePlatformUrls.put(bookCrawlerInfo.getSource(), bookCrawlerInfo.getUrl());
            }
        }

        if (CollectionUtils.isEmpty(salePlatformUrls)) {
            return;
        }

        Map<String, String> commonParams = rankService.getCommonParamsByBookIdAndAction(bookView.getBookId(), OperationEnums.update.getCode());
        if (CollectionUtils.isEmpty(commonParams)) {
            return;
        }

        try {
            if (CollectionUtils.isEmpty(salePlatformUrls)) {
                return;
            }

            String cacheKey = commonParams.get("key");
            Long count = redisClientService.getNumber(cacheKey);
            if (count == null || count == 0) {
                redisClientService.setNumber(cacheKey, 9L);

                if (salePlatformUrls.containsKey(CrawlerSourceEnums.amazon.getType())) {
                    Map<String, String> params = commonParams;
                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.amazon.getType()));
                    pushToQueue(amazonQueue, params);
                } else {
                    redisClientService.incrBy(cacheKey, -1L);
                }

//                if (salePlatformUrls.containsKey(CrawlerSourceEnums.jd.getType())) {
//                    Map<String, String> params = commonParams;
//                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.jd.getType()));
//                    pushToQueue(jdQueue, params);
//                } else {
//                    redisClientService.incrBy(cacheKey, -1L);
//                }

                if (salePlatformUrls.containsKey(CrawlerSourceEnums.dangdang.getType())) {
                    Map<String, String> params = commonParams;
                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.dangdang.getType()));
                    pushToQueue(dangdangQueue, params);
                } else {
                    redisClientService.incrBy(cacheKey, -1L);
                }

                if (salePlatformUrls.containsKey(CrawlerSourceEnums.douban.getType())) {
                    Map<String, String> params = commonParams;
                    params.put("url", salePlatformUrls.get(CrawlerSourceEnums.douban.getType()));
                    pushToQueue(doubanQueue, params);
                } else {
                    redisClientService.incrBy(cacheKey, -1L);
                }

                Map<String, String> params = commonParams;

                params.put("name", "《" + bookView.getName() + "》 " + bookView.getAuthor());
                pushToQueue(newsSougouQueue, params);
                pushToQueue(s360IndexQueue, params);

                HotWord hotWord = hotWordService.getById(bookView.getBookId());
                if (hotWord != null) {
                    params.put("cookie", hotWord.getCookie());
                    pushToQueue(wbIndexQueue, params);
                } else {
                    redisClientService.incrBy(cacheKey, -1L);
                }

                pushToQueue(bsKeyWordQueue, params);
                pushToQueue(wbKeyWordQueue, params);
                pushToQueue(wxKeyWordQueue, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pushToQueue(String queueName, Map<String, String> params) {
        queueService.push(queueName, params);
    }

}
