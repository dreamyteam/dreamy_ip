package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.sougou.NewsSougouHandler;
import com.dreamy.crawler.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/5/11.
 * 新闻媒体 news.sogou.com上5大新闻来源的新闻数量
 */
public class NewsMediaQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(NewsMediaQueueHandler.class);

    @Autowired
    NewsSougouHandler newsSougouHandler;
    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {

        String title = jsonObject.getString("name");
        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        String isbn = jsonObject.getString("isbn");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");
        try {
            newsSougouHandler.crawler(title, bookId);
        } catch (Exception e) {
            log.warn("NewsMediaQueueHandler  failed: bookId:" + bookId + " word:" + bookId);
        } finally {
            crawlerService.check(key, bookId);
        }


    }

}
