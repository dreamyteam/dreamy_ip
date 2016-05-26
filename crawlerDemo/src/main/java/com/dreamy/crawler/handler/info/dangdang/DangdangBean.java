package com.dreamy.crawler.handler.info.dangdang;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class DangdangBean {
    @ExtractBy("//ul[@class=\"bigimg\"]//li//a[@class=\"pic\"]/@href")
    private List<String> urls;
    public List<String> getUrls() {
        return urls;
    }
}
