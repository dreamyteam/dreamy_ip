package com.dreamy.handler.jd;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class JdBean {
    @ExtractBy("//li[@class=\"gl-item\"]//div[@class=\"gl-i-wrap\"]//div[@class=\"p-img\"]//a/@href")
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }


}
