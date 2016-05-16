package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Component
public class DangDangBookISBMQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Override
    public void consume(JSONObject jsonObject) {


        String isbn = jsonObject.getString("isbn");
        System.out.println("dangdang---"+isbn);
    }
}

