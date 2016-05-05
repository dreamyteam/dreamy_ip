package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.so.SoHandler;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.dao.BookIndexDataDao;
import com.dreamy.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangyongxing on 16/5/5.
 */
@Component
public class SoIndexEventQueueHandler extends AbstractQueueHandler {


    private static final Logger log = LoggerFactory.getLogger(SoIndexEventQueueHandler.class);

    @Autowired
    private SoHandler soHandler;

    @Autowired
    BookIndexDataDao bookIndexDataDao;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("source");
        Integer ipId=jsonObject.getInteger("bookId");
        String word=jsonObject.getString("word");
        try {
            BookIndexData bookIndexData = soHandler.getByUrl(word, "全国");
            bookIndexData.setId(ipId);
            bookIndexData.setSource(type);
            bookIndexDataDao.updateInser(bookIndexData);
            Thread.sleep(NumberUtils.randomInt(30000,50000));
        }catch (Exception e){
            log.warn("SoIndexEventQueueHandler  failed: bookId:" + ipId+" word:"+word);
        }


    }

}
