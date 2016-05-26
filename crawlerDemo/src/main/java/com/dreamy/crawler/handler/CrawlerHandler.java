package com.dreamy.crawler.handler;

import com.dreamy.mogodb.beans.BookInfo;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface CrawlerHandler {



    public Integer getId();

    /**
     * 解析链接获得信息
     *
     * @param url
     * 目标链接地址
     * @return
     */
    public BookInfo crawler(String url);


}
