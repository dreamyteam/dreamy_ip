package com.dreamy.handler.book;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/12.
 */
public class DouBan {

    @ExtractBy("//ul[@class=\"subject-list\"]//li//div[@class=\"pic\"]//a/@href")
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }
}
