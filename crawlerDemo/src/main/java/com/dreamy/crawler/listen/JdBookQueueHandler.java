package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.jd.JdCrawlerBookHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
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
public class JdBookQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(JdBookQueueHandler.class);
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private JdCrawlerBookHandler jdCrawlerBookHandler;



    @Override
    public void consume(JSONObject jsonObject) {
        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        String isbn = jsonObject.getString("isbn");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");
        BookInfo bookInfo = null;
        //判断动作  crawler 抓取 update 更新
        if (operation.equals(OperationEnums.crawler.getCode())){
            bookInfo = jdCrawlerBookHandler.getByISBN(isbn, operation);
        } else {
            bookInfo = jdCrawlerBookHandler.crawler(url, operation);
        }
        crawlerService.operationBook(operation, key, bookInfo, bookId, url,isbn,CrawlerSourceEnums.jd.getType());
    }



}

