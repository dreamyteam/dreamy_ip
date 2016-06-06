package com.dreamy.crawler.handler.info.netbook.qd;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/30.
 */
public class QiDian {

    //@ExtractBy("//div[@class=\"list_box\"]//tr//td//a[@class=\"name\"]/@href")
    @ExtractBy("//div[@class=\"swb\"]//span[@class=\"swbt\"]//a/@href")
    private List<String> urls;
    // @ExtractBy("//div[@class=\"list_box\"]//tr//td//a[@class=\"name\"]/text()")
   @ExtractBy("//div[@class=\"swb\"]//span[@class=\"swbt\"]//a/text()")
    private List<String> titles;


    //@ExtractBy("//div[@class=\"list_box\"]//tr//td//a[@class=\"author\"]/text()")
    @ExtractBy("//div[@class=\"swd\"]//a/text()")
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
