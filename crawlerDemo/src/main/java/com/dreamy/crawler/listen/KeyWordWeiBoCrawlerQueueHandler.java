package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.keyword.KeyWordWeiBoHandler;
import com.dreamy.crawler.service.CrawlerService;
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

    private static final Logger log = LoggerFactory.getLogger(KeyWordWeiBoCrawlerQueueHandler.class);

    @Autowired
    KeyWordWeiBoHandler keyWordWeiBoHandler;
    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {
        String word = jsonObject.getString("search_keyword");
        Integer bookId = jsonObject.getInteger("bookId");
        String key = jsonObject.getString("key");
        Integer ipType = Integer.parseInt(jsonObject.getString("type"));

        try {
            keyWordWeiBoHandler.crawler(word, bookId);
        } catch (Exception e) {
            log.error(" KeyWordWeiBoCrawlerQueueHandler 处理异常JSON[" + jsonObject + "]", e);
        } finally {
            crawlerService.check(key, bookId, ipType);
            try {
                Thread.sleep(NumberUtils.randomInt(1, 3) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

