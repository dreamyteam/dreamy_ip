package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.handler.AmazonCrawlerHandler;
import com.dreamy.handler.CrawlerHandler;
import com.dreamy.handler.CrawlerManage;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
/**
 * Created by wangyongxing on 16/4/18.
 */
@Component
public class CrawlerAmazonQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerAmazonQueueHandler.class);

    private Date lastRuntime = null;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private AmazonCrawlerHandler amazonCrawlerHandler;

    @Override
    public void consume(JSONObject jsonObject) {
        //@todo 延时机制

        if (lastRuntime == null) {
            lastRuntime = new Date();
        }

        Random random = new Random();
        Integer timeRange = 2000 + random.nextInt(2000);
        while (true) {
            Date currentTime = new Date();
            if (TimeUtils.diff(lastRuntime, currentTime) > (long) timeRange) {
                lastRuntime = currentTime;
                doConsume(jsonObject);
                break;
            }
        }
    }

    /**
     * @param jsonObject
     */
    private void doConsume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("type");
        String url = jsonObject.getString("url");
        Integer ipId = jsonObject.getInteger("ipId");
        Integer crawlerId = jsonObject.getInteger("crawlerId");

        BookCrawlerInfo bookCrawlerInfo = bookCrawlerInfoService.getById(crawlerId);
        try {

            BookInfo bookInfo =amazonCrawlerHandler.getByUrl(url);
            if (bookInfo != null) {
                bookInfo.setCrawlerId(crawlerId);
                bookInfo.setSource(type);
                bookInfo.setIpId(ipId);
                bookInfoService.updateInser(bookInfo);
                bookCrawlerInfo.setStatus(CrawlerTaskStatusEnums.success.getStatus());
            } else {
                bookCrawlerInfo.setStatus(CrawlerTaskStatusEnums.failed.getStatus());
                log.warn("amazon crawler event failed: type:" + type + ",url:" + url + ",id:" + crawlerId);
            }
            bookCrawlerInfoService.update(bookCrawlerInfo);
        } catch (Exception e) {
            bookCrawlerInfo.setStatus(CrawlerTaskStatusEnums.failed.getStatus());
            log.error(" amazon crawler event exception" + type + ",url:" + url + ",id:" + crawlerId, e);
            bookCrawlerInfoService.update(bookCrawlerInfo);
        }
    }
}
