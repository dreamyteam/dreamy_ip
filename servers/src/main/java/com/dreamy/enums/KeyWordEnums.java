package com.dreamy.enums;

/**
 * Created by wangyongxing on 16/4/29.
 */
public enum KeyWordEnums {

    baidu(1, "百度"),
    so(2, "360"),
    weibo(3, "微博"),
    weixin(4, "微信文章");
    private Integer type;

    private String description;

    KeyWordEnums(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
