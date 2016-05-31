package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.handler.jd.JdCrawlerBookHandler;
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
public class JdBookISBMQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);


    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private JdCrawlerBookHandler jdCrawlerBookHandler;
    @Resource
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Override
    public void consume(JSONObject jsonObject) {
        String isbn = jsonObject.getString("isbn");
        Integer bookId = jsonObject.getInteger("bookId");
        BookInfo bookInfo = jdCrawlerBookHandler.getByISBN(isbn);
        if (bookInfo != null) {
            bookInfo.setSource(CrawlerSourceEnums.jd.getType());
            bookInfo.setISBN(isbn);
            bookInfo.setIpId(bookId);
            bookInfo.setId(isbn + "_" + CrawlerSourceEnums.jd.getType());
            bookInfoService.updateInser(bookInfo);

            BookCrawlerInfo bookCrawlerInfo=new BookCrawlerInfo();
            bookCrawlerInfo.status(1);
            bookCrawlerInfo.bookId(bookId);
            bookCrawlerInfo.setSource(CrawlerSourceEnums.jd.getType());
            bookCrawlerInfo.url(bookInfo.getUrl());
            bookCrawlerInfoService.save(bookCrawlerInfo);
        }

    }

}

