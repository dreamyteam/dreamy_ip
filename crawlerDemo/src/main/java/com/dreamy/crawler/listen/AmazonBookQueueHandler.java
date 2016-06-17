package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.amazon.AmazonCrawlerBookHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class AmazonBookQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(AmazonBookQueueHandler.class);

    @Autowired
    private AmazonCrawlerBookHandler amazonCrawlerBookHandler;

    @Autowired
    private CrawlerService crawlerService;


    @Override
    public void consume(JSONObject jsonObject) {
        String isbn = jsonObject.getString("isbn");
        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");

        BookInfo bookInfo = null;
        //判断动作  crawler 抓取 update 更新
        if (operation.equals(OperationEnums.crawler.getCode())) {
            bookInfo = amazonCrawlerBookHandler.getByISBN(isbn,operation);
        } else {
            bookInfo = amazonCrawlerBookHandler.crawler(url,operation);
        }
        crawlerService.operationBook(operation, key, bookInfo, bookId, url,isbn,CrawlerSourceEnums.amazon.getType());
        try {
            Thread.sleep(NumberUtils.randomInt(1000,3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

