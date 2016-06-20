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
public class UpdateNetBookIndexTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateNetBookIndexTask.class);

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


    @Value("${queue_crawler_qidian}")
    private String qdQueue;

    @Value("${queue_crawler_qidian_mm}")
    private String qdMmQueue;


    @Value("${queue_crawler_zongheng}")
    private String zhQueue;

    @Value("${queue_crawler_huayu}")
    private String hyQueue;

    @Value("${queue_crawler_tieba}")
    private String tbQueue;

    @Value("${queue_index_wb}")
    private String wbIndexQueue;

    @Value("${queue_index_360}")
    private String s360IndexQueue;


    @Value("${queue_keyword_wx}")
    private String wxKeyWordQueue;

    @Value("${queue_keyword_wb}")
    private String wbKeyWordQueue;

    @Value("${queue_keyword_baidu}")
    private String baiduKeyWordQueue;

    @Value("${queue_keyword_so}")
    private String soKeyWordQueue;

    @Value("${queue_news_sougou}")
    private String newsSougouQueue;


    //    @Scheduled(cron = "0 55 16 * * ?")
    public void run() {
        LOGGER.info("start update rank job.." + TimeUtils.toString("yyyy-MM-dd HH:mm:ss", new Date()));
        int currentPage =25;
        Page page = new Page();
        page.setPageSize(500);

        while (true) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.net.getType());

                for (BookView bookView : bookViewList) {
                    updateByBookView(bookView);
                }
                if (!page.isHasNextPage()) {
                    break;
                }
                currentPage++;


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

        BookCrawlerInfo crawlerInfo = bookCrawlerInfoList.get(0);
        Map<String, String> commonParams = rankService.getCommonParamsByBookIdAndAction(bookView, OperationEnums.update.getCode());
        if (CollectionUtils.isEmpty(commonParams)) {
            return;
        }

        commonParams.put("type", IpTypeEnums.net.getType().toString());
        commonParams.put("url", crawlerInfo.getUrl());
        String cacheKey = commonParams.get("key");
        Long count = redisClientService.getNumber(cacheKey);
        if (count == null || count == 0) {
            redisClientService.setNumber(cacheKey, 8L);
            if (CrawlerSourceEnums.qidian.getType().equals(crawlerInfo.getSource())) {
                pushToQueue(qdMmQueue, commonParams);
            } else if (CrawlerSourceEnums.qidianmm.getType().equals(crawlerInfo.getSource())) {
                pushToQueue(qdMmQueue, commonParams);
            } else if (CrawlerSourceEnums.zongheng.getType().equals(crawlerInfo.getSource())) {
                pushToQueue(zhQueue, commonParams);
            } else if (CrawlerSourceEnums.huayu.getType().equals(crawlerInfo.getSource())) {
                pushToQueue(hyQueue, commonParams);
            } else {
                redisClientService.incrBy(cacheKey, -1L);
            }

            pushToQueue(tbQueue, commonParams);
            pushToQueue(newsSougouQueue, commonParams);
            pushToQueue(s360IndexQueue, commonParams);
            pushToQueue(soKeyWordQueue, commonParams);
            pushToQueue(wbKeyWordQueue, commonParams);
            pushToQueue(wxKeyWordQueue, commonParams);

            HotWord hotWord = hotWordService.getById(bookView.getBookId());
            if (hotWord != null) {
                commonParams.put("cookie", hotWord.getCookie());
                pushToQueue(wbIndexQueue, commonParams);
            } else {
                redisClientService.incrBy(cacheKey, -1L);
            }


        }
    }

    private void pushToQueue(String queueName, Map<String, String> params) {
        queueService.push(queueName, params);
    }

}
