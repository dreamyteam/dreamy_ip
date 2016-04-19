package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.CrawlerHandler;
import com.dreamy.handler.CrawlerManage;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: yaojiafeng
 * Date: 15/7/15
 * Time: 上午10:34
 */
@Component
public class CrawlerEventQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private CrawlerManage crawlerManage;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("type");
        String url = jsonObject.getString("url");
        Integer ipId=jsonObject.getInteger("ipId");
        CrawlerHandler handler = crawlerManage.getHandler(type);
        BookInfo bookInfo = (BookInfo) handler.getByUrl(url);
        if (bookInfo != null) {
            bookInfo.setIpId(ipId);
            bookInfoDao.save(bookInfo);
        } else {
            log.warn("crawler event failed: type:" + type + ",url:" + url);
        }
    }
}
