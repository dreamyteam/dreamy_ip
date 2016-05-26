package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.keyword.KeyWordWeiBoHandler;
import com.dreamy.handler.keyword.KeyWordWeiXinHandler;
import com.dreamy.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class KeyWordWeiBoCrawlerQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    KeyWordWeiBoHandler keyWordWeiBoHandler;

    @Override
    public void consume(JSONObject jsonObject) throws InterruptedException {

        Integer ipId = jsonObject.getInteger("bookId");
        String word = jsonObject.getString("word");
        keyWordWeiBoHandler.crawler(word, ipId);
        Thread.sleep(NumberUtils.randomInt(5, 10) * 1000);
    }
}

