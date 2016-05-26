package com.dreamy.crawler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.keyword.KeyWordHandler;
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

    private Date lastRuntime = null;

    @Override
    public void consume(JSONObject jsonObject) {

        if (lastRuntime == null) {
            lastRuntime = new Date();
        }

        int timeRange=NumberUtils.randomInt(8000,20000);
        while (true) {
            Date currentTime = new Date();
            if (TimeUtils.diff(lastRuntime, currentTime) > (long) timeRange) {
                lastRuntime = currentTime;
                //获取类型
                Integer type = jsonObject.getInteger("source");
                Integer ipId = jsonObject.getInteger("bookId");
                String word = jsonObject.getString("word");
                try {
                  keyWordHandler.crawler(word, ipId);
                } catch (Exception e) {
                    log.warn("keyWordHandler  failed: bookId:" + ipId + " word:" + word);
                }

                break;
            }
        }

    }
}

