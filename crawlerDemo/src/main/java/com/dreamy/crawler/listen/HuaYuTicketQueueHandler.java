package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.netbook.hy.HuaYuHandler;
import com.dreamy.crawler.handler.info.netbook.hy.HuaYuTicketHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/1.
 */
public class HuaYuTicketQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(HuaYuTicketQueueHandler.class);

    @Autowired
    HuaYuTicketHandler huaYuTicketHandler;


    @Override
    public void consume(JSONObject jsonObject) {
        Integer start = jsonObject.getInteger("start");
        Integer end = jsonObject.getInteger("end");
        huaYuTicketHandler.crawler(start, end);
    }
}
