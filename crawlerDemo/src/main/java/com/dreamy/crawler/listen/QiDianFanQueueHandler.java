package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.info.netbook.qd.FansHanlder;
import com.dreamy.crawler.handler.info.netbook.qd.QiDianHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.qidian.QiDianFan;
import com.dreamy.service.iface.mongo.QiDianFanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/1.
 */
public class QiDianFanQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(QiDianFanQueueHandler.class);

    @Autowired
    FansHanlder fansHanlder;
    @Autowired
    private QiDianFanService qiDianFanService;



    @Override
    public void consume(JSONObject jsonObject) {

        String url = jsonObject.getString("url");
        Integer bookId = jsonObject.getInteger("bookId");
        try {
            QiDianFan qiDianFan = fansHanlder.crawler(bookId, url);
            if (qiDianFan != null) {
                qiDianFanService.updateInser(qiDianFan);
            }

        } catch (Exception e) {
            log.warn("QiDianFanQueueHandler  failed: bookId:" + bookId + " url:" + url);
        } finally {

        }


    }
}
