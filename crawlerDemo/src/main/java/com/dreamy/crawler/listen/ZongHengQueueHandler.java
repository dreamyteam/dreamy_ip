package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.netbook.zh.ZongHengHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/1.
 */
public class ZongHengQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(ZongHengQueueHandler.class);

    @Autowired
    ZongHengHandler zongHengHandler;
    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {

        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");
        try {
            NetBookInfo netBookInfo = zongHengHandler.crawler(bookId, url, operation);
            crawlerService.operationNetBook(operation,key, netBookInfo, bookId, CrawlerSourceEnums.zongheng.getType());
        } catch (Exception e) {
            log.warn("ZongHengQueueHandler  failed: bookId:" + bookId + " url:" + url);
        } finally {
        }


    }
}
