package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.keyword.KeyWordHandler;
import com.dreamy.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        //获取类型
        Integer type = jsonObject.getInteger("source");
        Integer ipId = jsonObject.getInteger("bookId");
        String word = jsonObject.getString("word");
        try {
            keyWordHandler.crawler(word, ipId);
            Thread.sleep(NumberUtils.randomInt(50000, 100000));

        } catch (Exception e) {
            log.warn("keyWordHandler  failed: bookId:" + ipId + " word:" + word);
        }

    }
}

