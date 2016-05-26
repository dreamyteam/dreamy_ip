package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerTaskStatusEnums;
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
public class CrawlerEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    private Date lastRuntime = null;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private CrawlerManage crawlerManage;

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
        String isbn = jsonObject.getString("isbn");
        Integer crawlerId = jsonObject.getInteger("crawlerId");

        BookCrawlerInfo bookCrawlerInfo = bookCrawlerInfoService.getById(crawlerId);
        try {
            CrawlerHandler handler = crawlerManage.getHandler(type);
            BookInfo bookInfo = (BookInfo) handler.getByUrl(url);
            if (bookInfo != null) {
                bookInfo.setCrawlerId(crawlerId);
                bookInfo.setSource(type);
                bookInfo.setIpId(ipId);
                bookInfo.setId(isbn+"_"+type);
                bookInfoService.updateInser(bookInfo);
                bookCrawlerInfo.setStatus(CrawlerTaskStatusEnums.success.getStatus());
            } else {
                bookCrawlerInfo.setStatus(CrawlerTaskStatusEnums.failed.getStatus());
                log.warn("crawler event failed: type:" + type + ",url:" + url + ",id:" + crawlerId);
            }
            bookCrawlerInfoService.update(bookCrawlerInfo);
        } catch (Exception e) {
            bookCrawlerInfo.setStatus(CrawlerTaskStatusEnums.failed.getStatus());
            log.error("crawler event exception" + type + ",url:" + url + ",id:" + crawlerId, e);
            bookCrawlerInfoService.update(bookCrawlerInfo);
        }
    }
}
