package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.CommentHandler;
import com.dreamy.handler.CrawlerHandler;
import com.dreamy.handler.CrawlerManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class CommentEventQueueHandler  extends  AbstractQueueHandler{

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    private CommentHandler commentHandler;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("type");
        Integer ipId=jsonObject.getInteger("ipId");
        String url=jsonObject.getString("url");
        commentHandler.getByUrl(ipId,url);
    }
}
