package com.dreamy.crawler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.keyword.KeyWordHandler;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class KeyWordEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    KeyWordHandler keyWordHandler;

    @Override
    public void consume(JSONObject jsonObject) {
        Integer ipId = jsonObject.getInteger("bookId");
        String word = jsonObject.getString("word");
        try {
            keyWordHandler.crawler(word, ipId);
        } catch (Exception e) {
            log.warn("keyWordHandler  failed: bookId:" + ipId + " word:" + word);
        }


    }
}

