package com.dreamy.crawler.handler.info.netbook.zh;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/30.
 */
public class ZongHeng {
    @ExtractBy("//ul[@class=\"main_con\"]//li//span[@class=\"chap\"]//a[@class=\"fs14\"]/@href")
    private List<String> urls;

    @ExtractBy("//ul[@class=\"main_con\"]//li//span[@class=\"chap\"]//a[@class=\"fs14\"]/text()")
    private List<String> titles;

    @ExtractBy("//ul[@class=\"main_con\"]//li//span[@class=\"author\"]//a/text()")
    private List<String> authoers;

    @ExtractBy("//ul[@class=\"main_con\"]//li//span[@class=\"chap\"]//em[@class=\"mark\"]/text()")
    private List<String>  marks;

    public List<String> getUrls() {
        return urls;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getAuthoers() {
        return authoers;
    }
}
