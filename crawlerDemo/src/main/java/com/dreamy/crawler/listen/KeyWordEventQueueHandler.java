package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.dreamy.crawler.handler.keyword.KeyWordHandler;
import com.dreamy.crawler.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class KeyWordEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(KeyWordEventQueueHandler.class);

    @Autowired
    KeyWordHandler keyWordHandler;

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {

        String title = jsonObject.getString("name");
        Integer bookId = jsonObject.getInteger("bookId");
        String key = jsonObject.getString("key");
        Integer ipType = Integer.parseInt(jsonObject.getString("type"));

        try {
            keyWordHandler.crawler(title, bookId);
        } catch (Exception e) {
            log.warn("keyWordHandler failed: bookId:" +bookId + " word:" + title);
        }
        finally {
            crawlerService.check(key,bookId,ipType);
        }
    }

}

