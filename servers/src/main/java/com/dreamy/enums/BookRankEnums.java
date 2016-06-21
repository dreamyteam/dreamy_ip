package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午4:37
 */
public enum BookRankEnums {
    composite(1, "综合指数排名", "book_composite_rank"),
    develop(2, "潜力指数排行", "book_develop_rank"),
    propagation(3, "传播指数排行", "book_propagation_rank"),
    hot(4, "热度指数排行", "book_hot_rank"),

    composite_net(5, "网络文学综合指数排行", "book_composite_rank_net"),
    develop_net(6, "网络文学潜力指数排行", "book_develop_rank_net"),
    hot_net(7, "网络文学热度指数排行", "book_hot_rank_net"),
    propagation_net(8, "网络文学传播指数排行", "book_propagation_net"),
    activity_net(9, "网络文学活跃指数排行", "book_activity_rank_net");
    private Integer type;

    private String Description;

    private String cacheKey;

    BookRankEnums(Integer type, String description, String cacheKey) {
        this.type = type;
        Description = description;
        this.cacheKey = cacheKey;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
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
