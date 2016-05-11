package com.dreamy.handler.sougou;

import com.dreamy.utils.PatternUtils;
import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/20.
 */
public class NewsSougou {
    @ExtractBy("//div[@class=\"filt-sort filt-sort-from\"]//a/@href")
    private List<String> urls;

    @ExtractBy("//div[@class=\"mun\"]/text()")
    private String info;

    private String num;


    public String getNum() {
        return PatternUtils.getNum(info);
    }

    public List<String> getUrls() {
        return urls;
    }



    public String getInfo() {
        return info;
    }
}
