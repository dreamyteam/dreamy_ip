package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.douban.DouBanCrawlerBookHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class CrawlerDoubanBookQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerDoubanBookQueueHandler.class);

    @Autowired
    private DouBanCrawlerBookHandler douBanCrawlerBookHandler;
    @Autowired
    private CrawlerService crawlerService;


    @Override
    public void consume(JSONObject jsonObject) {

        String title = jsonObject.getString("title");
        String url = jsonObject.getString("url");
        String isbn = jsonObject.getString("isbn");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");
        try {
            BookInfo bookInfo = douBanCrawlerBookHandler.crawler(url,operation);
            if (bookInfo != null && StringUtils.isNotEmpty(bookInfo.getTitle())) {
                crawlerService.Operation(operation,key,bookInfo,title,bookId,url);
            }
            Thread.sleep(NumberUtils.randomInt(1000, 3000));
        } catch (Exception e) {
            log.error("CrawlerDoubanBookQueueHandler event exception" + title + ",url:" + url, e);

        }
    }
}
