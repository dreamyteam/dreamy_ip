package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.netbook.hy.HuaYuHandler;
import com.dreamy.crawler.handler.info.netbook.zh.ZongHengHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.mogodb.beans.NetBookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/1.
 */
public class HuaYuQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(HuaYuQueueHandler.class);

    @Autowired
    HuaYuHandler huaYuHandler;
    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");

        try {
            NetBookInfo netBookInfo = huaYuHandler.crawler(bookId, url, operation);
            crawlerService.operationNetBook(operation, key, netBookInfo, bookId);
        } catch (Exception e) {
            log.warn("HuaYuQueueHandler  failed: bookId:" + bookId + " url:" + url);
        } finally {
        }


    }
}
