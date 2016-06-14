package com.dreamy.mogodb.beans.history;

/**
 * Created by wangyongxing on 16/6/13.
 * 网络小说数据历史
 */
public class NetBookDataHistory {

    private String id;

    private Integer bookId;

    private Integer type;

    /**
     * 总点击数量
     */
    private Integer clickNum;

    /**
     * 总推荐数
     */
    private Integer recommendNum;

    /**
     * 月票
     */
    private Integer ticketNum;


    /**
     * 评分
     */
    private String score;


    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 月排名
     */
    private String monthSort;


    private String createDate;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getMonthSort() {
        return monthSort;
    }

    public void setMonthSort(String monthSort) {
        this.monthSort = monthSort;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
