package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;

/**
 * Created by wangyongxing on 16/5/20.
 */
public class HotWord {
    @Id
    private Integer id;
    private String wid;
    private String wname;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}