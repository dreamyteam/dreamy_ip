package com.dreamy.crawler;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.keyword.KeyWordWeiXinHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangyongxing on 16/4/28.
 * 微信文章关键字搜索
 */
@Component
public class KeyWordWeiXinEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    KeyWordWeiXinHandler keyWordWeiXinHandler;

    @Override
    public void consume(JSONObject jsonObject) {
        Integer ipId = jsonObject.getInteger("bookId");
        String word = jsonObject.getString("word");
        keyWordWeiXinHandler.crawler(word, ipId);


    }
}

