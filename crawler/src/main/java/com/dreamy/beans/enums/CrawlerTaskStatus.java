package com.dreamy.beans.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/18
 * Time: 下午7:22
 */
public enum CrawlerTaskStatus {
    waitting(1, "等待抓取"),
    starting(2, "抓取中"),
    finished(15, "抓取完成");

    private Integer status;

    private String description;

    CrawlerTaskStatus(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
