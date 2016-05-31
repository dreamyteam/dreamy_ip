package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * redis key 常量
 */
public enum RedisConstEnums {
    weiboCookieName("sinacookie", "微博账号cookie 前缀"),
    weibo("sina_cookies_size", "微博账号cookies"),
    sougouweixinCookieName("weixin_cookie", "搜狗微信cookie 前缀"),
    sougouweixin("weixin_cookies_map", "搜狗微信cookies"),
    proxIpList("proxy_ips_list", "代理ip");
    private String cacheKey;
    private String Description;


    RedisConstEnums(String cacheKey, String description) {
        this.cacheKey = cacheKey;
        this.Description = description;

    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
