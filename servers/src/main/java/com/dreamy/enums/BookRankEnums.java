package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午4:37
 */
public enum BookRankEnums {
    composite(1, "综合指数排名"),
    develop(2, "潜力指数排行"),
    propagation(3, "传播指数排行"),
    hot(4, "热度指数排行"),
    activity(5, "活跃指数排行");
    private Integer type;

    private String Description;

    BookRankEnums(Integer type, String description) {
        this.type = type;
        Description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
