package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.keyword.KeyWordWeiXinHandler;
import com.dreamy.crawler.service.CrawlerService;
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

    private static final Logger log = LoggerFactory.getLogger(KeyWordWeiXinEventQueueHandler.class);

    @Autowired
    KeyWordWeiXinHandler keyWordWeiXinHandler;

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {
        String word = jsonObject.getString("search_keyword");
        Integer bookId = jsonObject.getInteger("bookId");
        String key = jsonObject.getString("key");
        Integer ipType = Integer.parseInt(jsonObject.getString("type"));

        try {
            keyWordWeiXinHandler.crawler(word, bookId);
        } catch (Exception e) {
            log.error("KeyWordWeiXinEventQueueHandler 处理异常JSON[" + jsonObject + "]", e);
        } finally {
            crawlerService.check(key, bookId, ipType);
        }


    }
}

