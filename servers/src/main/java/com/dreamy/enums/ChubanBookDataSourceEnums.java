package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午7:07
 */
public enum ChubanBookDataSourceEnums {
    amazon(1, "亚马逊"),
    jd(2, "京东"),
    dangdang(3, "当当"),
    douban(4, "豆瓣"),
    weibo(5, "微博"),
    weixin(6, "微信"),
    s360(7, "360"),
    baidu(8, "baidu"),
    sougou(9, "搜狗"),;

    private Integer source;

    private String description;

    ChubanBookDataSourceEnums(Integer source, String description) {
        this.source = source;
        this.description = description;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
