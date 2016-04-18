package com.dreamy.beans.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/18
 * Time: 下午7:18
 */
public enum CrawlerSourceEnums {
    amazon(1, "亚马逊"),
    jd(2, "京东"),
    dangdang(3, "当当"),
    douban(4, "豆瓣");

    private Integer type;

    private String description;

    CrawlerSourceEnums(Integer type, String description) {
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
