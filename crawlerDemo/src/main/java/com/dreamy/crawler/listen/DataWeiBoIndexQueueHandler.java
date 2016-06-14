package com.dreamy.crawler.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.crawler.handler.so.SoHandler;
import com.dreamy.crawler.handler.weibo.DataWeiBoHandler;
import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.dao.BookIndexDataDao;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangyongxing on 16/5/5.
 */
@Component
public class DataWeiBoIndexQueueHandler extends AbstractQueueHandler {


    private static final Logger log = LoggerFactory.getLogger(DataWeiBoIndexQueueHandler.class);

    @Autowired
    private DataWeiBoHandler dataWeiBoHandler;

    @Autowired
    BookIndexDataDao bookIndexDataDao;

    @Autowired
    private CrawlerService crawlerService;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        String title = jsonObject.getString("name");
        Integer bookId = jsonObject.getInteger("bookId");
        String key = jsonObject.getString("key");
        String cookie = jsonObject.getString("cookie");
        Integer ipType = Integer.parseInt(jsonObject.getString("type"));

        try {
            BookIndexData bookIndexData = dataWeiBoHandler.crawler(cookie);
            if (StringUtils.isNotEmpty(bookIndexData.getMale())) {
                bookIndexData.setId(bookId+"_"+ IndexSourceEnums.weibo.getType());
                bookIndexData.setBookId(bookId);
                bookIndexData.setSource(IndexSourceEnums.weibo.getType());
                bookIndexData.setUpdatedAt(new Date());
                bookIndexDataDao.updateInser(bookIndexData);
                crawlerService.saveBookIndexDataHistory(bookIndexData);
            }
        } catch (Exception e) {
            log.error("DataWeiBoIndexQueueHandler  failed: bookId:" + bookId + " word:" + title, e);
        } finally {
            crawlerService.check(key, bookId,ipType);
            try {
                Thread.sleep(NumberUtils.randomInt(1000, 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
