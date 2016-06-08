package com.dreamy.crawler.handler.info.netbook.hy;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/2.
 */
public class HuaYu {

    @ExtractBy("//div[@class=\"table_con\"]//ul//li//span[@class=\"book\"]//a[@class=\"f14\"]/@href")
    private List<String> urls;

    @ExtractBy("//div[@class=\"table_con\"]//ul//li//span[@class=\"click\"]/text()")
    private List<String> num;

    public List<String> getUrls() {
        return urls;
    }

    public List<String> getNum() {
        return num;
    }
}
