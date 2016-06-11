package com.dreamy.crawler.handler.info.netbook.qd;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/8.
 */
public class Info {

    @ExtractBy("//div[@class=\"box\"]//h2//a/@title")
    private List<String> names;

    @ExtractBy("//div[@class=\"box\"]//p/text()")
    private List<String> infos;


    public List<String> getInfos() {
        return infos;
    }

    public List<String> getNames() {
        return names;
    }
}
