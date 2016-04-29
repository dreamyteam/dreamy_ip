package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 下午4:18
 */
public enum IndexSourceEnums {
    baidu(1, "百度", "baidu"),
    s360(2, "360", "360"),
    weibo(3, "微博", "weibo");

    private Integer type;

    private String description;

    private String name;

    IndexSourceEnums(Integer type, String description, String name) {
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
