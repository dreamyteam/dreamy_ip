package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.handler.CrawlerHandler;
import com.dreamy.handler.CrawlerManage;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: yaojiafeng
 * Date: 15/7/15
 * Time: 上午10:34
 */
@Component
public class CrawlerEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private CrawlerManage crawlerManage;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("type");
        String url = jsonObject.getString("url");
        Integer ipId = jsonObject.getInteger("ipId");
        Integer crawlerId = jsonObject.getInteger("crawlerId");

        BookCrawlerInfo bookCrawlerInfo = bookCrawlerInfoService.getById(crawlerId);

        try {

            BookInfo old = bookInfoService.getById(crawlerId);
            if (old != null) {
                bookInfoService.delById(crawlerId);
            }

            CrawlerHandler handler = crawlerManage.getHandler(type);
            BookInfo bookInfo = (BookInfo) handler.getByUrl(url);
            if (bookInfo != null) {
                bookInfo.setCrawlerId(crawlerId);
                bookInfo.setSource(type);
                bookInfo.setIpId(ipId);
                bookInfoService.saveByRecord(bookInfo);

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
