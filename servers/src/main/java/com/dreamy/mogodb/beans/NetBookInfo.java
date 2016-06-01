package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;

/**
 * Created by wangyongxing on 16/5/30.
 */
public class NetBookInfo {

    @Id
    private Integer bookId;

    /**
     * 作品名称
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 图片
     */
    private String image;

    /**
     * 月排名
     */
    private String monthSort;
    /**
     * 总点击数量
     */
    private Integer clickNum;
    /**
     * 总推荐数
     */
    private Integer recommendNum;
    /**
     * 是否完结
     */
    private String over;
    /**
     * 简介
     */
    private String info;

    /**
     * 月票
     */
    private Integer ticketNum;

    /**
     * 作品分类
     */
    private String category;

    /**
     * 标签
     */
    private String label;

    /**
     * 评分
     */
    private String score;
    /**
     * 授权状态
     */
    private String authority;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getMonthSort() {
        return monthSort;
    }

    public void setMonthSort(String monthSort) {
        this.monthSort = monthSort;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(Integer recommendNum) {
        this.recommendNum = recommendNum;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
