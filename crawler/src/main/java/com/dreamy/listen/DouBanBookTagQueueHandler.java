package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.handler.book.DouBanBookCrawlerHandler;
import com.dreamy.handler.book.DouBanBookHandler;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.CrawlerService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class DouBanBookTagQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(DouBanBookTagQueueHandler.class);


    DouBanBookHandler douBanBookHandler;


    @Override
    public void consume(JSONObject jsonObject) {

        String title = jsonObject.getString("title");
        douBanBookHandler.crawler(title);
    }

}
