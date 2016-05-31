package com.dreamy.crawler.handler.info.netbook.qd;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/30.
 */
public class QiDian {

    //@ExtractBy("//div[@class=\"swb\"]//span[@class=\"swbt\"]//a/@href")
    @ExtractBy("//div[@class=\"list_box\"]//tr//td//a[@class=\"name\"]/@href")
    private List<String> urls;

   // @ExtractBy("//div[@class=\"swb\"]//span[@class=\"swbt\"]//a/text()")
    @ExtractBy("//div[@class=\"list_box\"]//tr//td//a[@class=\"name\"]/text()")
    private List<String> titles;

    //@ExtractBy("//div[@class=\"swd\"]//a/text()")
    @ExtractBy("//div[@class=\"list_box\"]//tr//td//a[@class=\"author\"]/text()")
    private List<String> authoers;

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
