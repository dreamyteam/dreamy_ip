package com.dreamy.crawler.handler.info.amazon;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class AmazonBean {


    @ExtractBy("//ul[@class=\"s-result-list\"]//li//div[@class=\"a-column a-span12 a-text-center\"]//a/@href")
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }
}
