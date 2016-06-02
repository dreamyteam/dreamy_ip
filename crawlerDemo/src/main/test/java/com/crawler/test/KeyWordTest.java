package com.crawler.test;

import com.dreamy.crawler.handler.info.douban.DouBanCrawlerBookHandler;
import com.dreamy.crawler.handler.keyword.KeyWordHandler;
import com.dreamy.enums.OperationEnums;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/6/2.
 */
public class KeyWordTest extends BaseJunitTest {

    @Autowired
    KeyWordHandler keyWordHandler;
    @Autowired
    DouBanCrawlerBookHandler douBanCrawlerBookHandler;

    @Test
    public void crawler(){
        douBanCrawlerBookHandler.crawler("https://book.douban.com/subject/1236122/", OperationEnums.crawler.getCode());
//        keyWordHandler.crawler("你们 我们 他们",5321);

    }
}
