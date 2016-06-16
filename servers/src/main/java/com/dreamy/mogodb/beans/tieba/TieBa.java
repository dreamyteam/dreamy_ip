package com.dreamy.mogodb.beans.tieba;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by wangyongxing on 16/6/12.
 */
public class TieBa {
    @Id
    private Integer bookId;
    /**
     * 关注数
     */
    private Integer followNum;
    /**
     * 帖子
     */
    private Integer postNum;
    /**
     * 人气排行
     */
    private Integer popularitySort;
    /**
     * 吧名
     */
    private String title;

    private String name;

    private String code;
    /**
     * 目录
     */
    private String category;

    private Date updatedAt;


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getPopularitySort() {
        return popularitySort;
    }

    public void setPopularitySort(Integer popularitySort) {
        this.popularitySort = popularitySort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
