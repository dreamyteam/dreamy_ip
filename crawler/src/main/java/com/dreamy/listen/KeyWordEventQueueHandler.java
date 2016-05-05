package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.handler.keyword.KeyWordHandler;
import com.dreamy.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * Created by wangyongxing on 16/4/28.
 */
public class KeyWordEventQueueHandler extends AbstractQueueHandler  {

    private static final Logger log = LoggerFactory.getLogger(CrawlerEventQueueHandler.class);

    @Autowired
    KeyWordHandler keyWordHandler;

    @Override
    public void consume(JSONObject jsonObject) {
        //获取类型
        Integer type = jsonObject.getInteger("source");
        Integer ipId=jsonObject.getInteger("bookId");
        String word=jsonObject.getString("word");
        try {
            keyWordHandler.crawler(word, ipId);
            Thread.sleep(NumberUtils.randomInt(30000,50000));

        }catch (Exception e){
            log.warn("keyWordHandler  failed: bookId:" + ipId+" word:"+word);
        }


    }
    private static int getRandom(int size){
        Random random=new Random();
        int result=random.nextInt(size);
        return result;
    }
}
