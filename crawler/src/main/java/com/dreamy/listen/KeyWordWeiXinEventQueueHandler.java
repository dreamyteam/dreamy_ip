package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.keyword.KeyWordHandler;
import com.dreamy.handler.keyword.KeyWordWeiXinHandler;
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
public class KeyWordWeiXinEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    KeyWordWeiXinHandler keyWordWeiXinHandler;

    @Override
    public void consume(JSONObject jsonObject) {

        Integer type = jsonObject.getInteger("source");
        Integer ipId = jsonObject.getInteger("bookId");
        String word = jsonObject.getString("word");
        keyWordWeiXinHandler.crawler(word, ipId);


    }
}

