package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.netbook.qd.QiDianHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.mogodb.beans.NetBookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/1.
 */
public class QiDianQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(QiDianQueueHandler.class);

    @Autowired
    QiDianHandler qiDianHandler;
    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {

        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");
        try {
            NetBookInfo netBookInfo = qiDianHandler.crawler(bookId, url, operation);
            crawlerService.operationNetBook(operation,key, netBookInfo, bookId);

        } catch (Exception e) {
            log.warn("QiDianQueueHandler  failed: bookId:" + bookId + " url:" + url);
        } finally {

        }


    }
}
