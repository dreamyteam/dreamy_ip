package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.douban.DouBanCrawlerBookHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class DoubanBookQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(DoubanBookQueueHandler.class);

    @Autowired
    private DouBanCrawlerBookHandler douBanCrawlerBookHandler;
    @Autowired
    private CrawlerService crawlerService;


    @Override
    public void consume(JSONObject jsonObject) {
        String title = jsonObject.getString("name");
        String url = jsonObject.getString("url");
        String isbn = jsonObject.getString("isbn");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");
        try {
            BookInfo bookInfo = douBanCrawlerBookHandler.crawler(url, operation);
            crawlerService.operation(operation, key, bookInfo, title, bookId, url,isbn, CrawlerSourceEnums.douban.getType());
            Thread.sleep(NumberUtils.randomInt(1000, 3000));
        } catch (Exception e) {
            log.error("DoubanBookQueueHandler event exception" + title + ",url:" + url, e);

        }
    }
}
