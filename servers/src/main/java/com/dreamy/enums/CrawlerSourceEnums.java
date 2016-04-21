package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/18
 * Time: 下午7:18
 */
public enum CrawlerSourceEnums {
    amazon(1, "亚马逊","amazon"),
    jd(2, "京东","jd"),
    dangdang(3, "当当","dd"),
    douban(4, "豆瓣","douban");

    private Integer type;

    private String description;

    private String name;

    CrawlerSourceEnums(Integer type, String description,String name) {
        this.type = type;
        this.description = description;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
