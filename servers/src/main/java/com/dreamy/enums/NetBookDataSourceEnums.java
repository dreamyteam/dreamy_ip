package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午7:07
 */
public enum NetBookDataSourceEnums {
    qd(1, "起点"),
    qd_mm(2, "起点mm"),
    zh(3, "纵横"),
    hy(4, "花语"),
    weibo(5, "微博"),
    weixin(6, "微信"),
    s360(7, "360"),
    baidu(8, "baidu"),
    sougou(9, "搜狗");

    private Integer source;

    private String description;

    NetBookDataSourceEnums(Integer source, String description) {
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
