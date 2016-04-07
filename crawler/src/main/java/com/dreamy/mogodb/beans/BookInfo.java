package com.dreamy.mogodb.beans;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/5.
 */
public class BookInfo {
    /**
     * 作品名称
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版时间
     */
    private String pushTime;
    /**
     * 出版社
     */
    private String press;
    /**
     * 销售排名
     */
    private String saleSort;
    /**
     * 总点击数量
     */
    private Integer clickNum;
    /**
     * 封面
     */
    private String image;
    /**
     * 作品简介
     */
    private String info;

    private String type;
    /**
     * 标签
     */
    private String lable;

    /**
     * 评分
     */
    private Double score;
    /**
     * 作者简介
     */
    private String authorInfo;

    private List<Comment> commentList;

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getSaleSort() {
        return saleSort;
    }

    public void setSaleSort(String saleSort) {
        this.saleSort = saleSort;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }
}
