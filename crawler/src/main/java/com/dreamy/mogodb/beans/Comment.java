package com.dreamy.mogodb.beans;

/**
 * Created by wangyongxing on 16/4/5.
 */
public class Comment {

    private Integer ipId;

    private String url;

    private String content;

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

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
}
