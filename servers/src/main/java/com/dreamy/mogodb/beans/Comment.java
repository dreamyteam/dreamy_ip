package com.dreamy.mogodb.beans;

/**
 * Created by wangyongxing on 16/4/5.
 */
public class Comment {


    private String url;

    private String content;

    private String createTime;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
