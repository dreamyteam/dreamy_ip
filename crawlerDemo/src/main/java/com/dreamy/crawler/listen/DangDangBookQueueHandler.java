package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.dangdang.DangDangCrawlerBookHandler;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class DangDangBookQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(DangDangBookQueueHandler.class);

    @Autowired
    private DangDangCrawlerBookHandler dangDangCrawlerBookHandler;
    @Autowired
    private CrawlerService crawlerService;


    @Override
    public void consume(JSONObject jsonObject) {
        String url = jsonObject.getString("url");
        String isbn = jsonObject.getString("isbn");
        Integer bookId = jsonObject.getInteger("bookId");
        String operation = jsonObject.getString("operation");
        String key = jsonObject.getString("key");

        BookInfo bookInfo = null;
        //判断动作  crawler 抓取 update 更新
        if (operation.equals(OperationEnums.crawler.getCode())) {
            bookInfo = dangDangCrawlerBookHandler.getByISBN(isbn, operation);
        } else {
            bookInfo = dangDangCrawlerBookHandler.crawler(url, operation);
        }
        crawlerService.operationBook(operation, key, bookInfo, bookId, url,isbn,CrawlerSourceEnums.dangdang.getType());

    }

}

