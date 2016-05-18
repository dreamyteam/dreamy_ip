package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.handler.dangdang.DangDangCrawlerBookHandler;
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
public class DangDangBookISBMQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    private DangDangCrawlerBookHandler dangDangCrawlerBookHandler;

    @Autowired
    private BookInfoService bookInfoService;

    @Resource
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Override
    public void consume(JSONObject jsonObject) {
        String isbn = jsonObject.getString("isbn");
        Integer bookId = jsonObject.getInteger("bookId");
        BookInfo bookInfo = dangDangCrawlerBookHandler.getByISBN(isbn);
        if (bookInfo != null) {
            bookInfo.setSource(CrawlerSourceEnums.dangdang.getType());
            bookInfo.setISBN(isbn);
            bookInfo.setId(isbn + "_" + CrawlerSourceEnums.dangdang.getType());
            bookInfoService.updateInser(bookInfo);
            BookCrawlerInfo bookCrawlerInfo=new BookCrawlerInfo();
            bookCrawlerInfo.status(1);
            bookCrawlerInfo.bookId(bookId);
            bookCrawlerInfo.setSource(CrawlerSourceEnums.dangdang.getType());
            bookCrawlerInfo.url(bookInfo.getUrl());
            bookCrawlerInfoService.save(bookCrawlerInfo);
        }

    }

}

