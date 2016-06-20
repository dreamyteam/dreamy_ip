package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.keyword.KeyWordHandler;
import com.dreamy.crawler.handler.keyword.KeyWordSoHandler;
import com.dreamy.crawler.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class KeyWordSoQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(KeyWordSoQueueHandler.class);

    @Autowired
    KeyWordSoHandler keyWordSoHandler;

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {

        String word = jsonObject.getString("word");
        String key = jsonObject.getString("key");
        Integer bookId = jsonObject.getInteger("bookId");
        Integer ipType = Integer.parseInt(jsonObject.getString("type"));

        try {
            keyWordSoHandler.crawler(word, bookId);
        } catch (Exception e) {
            log.warn("keyWordHandler failed: bookId:" + bookId + " word:" + word);
        } finally {
            crawlerService.check(key, bookId, ipType);
        }
    }

}

