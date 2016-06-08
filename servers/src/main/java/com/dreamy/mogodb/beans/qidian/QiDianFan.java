package com.dreamy.mogodb.beans.qidian;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/8.
 */
public class QiDianFan {
    @Id
    private Integer bookId;

    private Integer followerCount;//f粉丝数


    private Integer followingCount;//关注

    private Integer twitterCount;//广播

    private List<FanInfo> list;

    public Integer getTwitterCount() {
        return twitterCount;
    }

    public void setTwitterCount(Integer twitterCount) {
        this.twitterCount = twitterCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getBookId() {
        return bookId;
    }

    public List<FanInfo> getList() {
        return list;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setList(List<FanInfo> list) {
        this.list = list;
    }
}
