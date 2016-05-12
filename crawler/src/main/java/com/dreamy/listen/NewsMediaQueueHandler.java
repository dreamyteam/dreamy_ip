package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.handler.CrawlerManage;
import com.dreamy.handler.sougou.NewsSougouHandler;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyongxing on 16/5/11.
 * 新闻媒体 news.sogou.com上5大新闻来源的新闻数量
 */
public class NewsMediaQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(NewsMediaQueueHandler.class);

    @Autowired
    NewsSougouHandler newsSougouHandler;


    @Override
    public void consume(JSONObject jsonObject) {

        Integer type = jsonObject.getInteger("source");
        Integer bookId = jsonObject.getInteger("bookId");
        String word = jsonObject.getString("word");
        try {
            newsSougouHandler.crawler(word, bookId);
        } catch (Exception e) {
            log.warn("NewsMediaQueueHandler  failed: bookId:" + bookId + " word:" + word);
        }



    }

}
