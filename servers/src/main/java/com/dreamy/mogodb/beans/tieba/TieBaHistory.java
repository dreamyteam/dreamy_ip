package com.dreamy.mogodb.beans.tieba;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by wangyongxing on 16/6/12.
 */
public class TieBaHistory {

    @Id
    @Field("_id")
    private String id;

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

    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPopularitySort() {
        return popularitySort;
    }

    public void setPopularitySort(Integer popularitySort) {
        this.popularitySort = popularitySort;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
