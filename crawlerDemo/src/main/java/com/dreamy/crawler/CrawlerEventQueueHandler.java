package com.dreamy.crawler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.CrawlerHandler;
import com.dreamy.crawler.handler.CrawlerManage;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    private CrawlerManage crawlerManage;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("type");
        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("ipId");
        try {
            CrawlerHandler handler = crawlerManage.getHandler(type);
            BookInfo bookInfo = (BookInfo) handler.crawler(url);
            if (bookInfo != null) {
                bookInfo.setSource(type);
                bookInfo.setIpId(bookId);
                bookInfoService.updateInser(bookInfo);
            } else {
                log.warn("crawler event failed: type:" + type + ",url:" + url + ",ipId:" + bookId);
            }

        } catch (Exception e) {
            log.error("crawler event exception" + type + ",url:" + url + ",ipId:" + bookId, e);

        }
    }
}
