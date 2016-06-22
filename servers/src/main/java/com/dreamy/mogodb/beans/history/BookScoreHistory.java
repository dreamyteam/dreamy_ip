package com.dreamy.mogodb.beans.history;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class BookScoreHistory {

    @Id
    private String id;

    private Integer bookId;

    private Integer source;


    private Integer commentNum;

    private Integer saleSort;

    private String date;

    private Double score;

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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getSaleSort() {
        return saleSort;
    }

    public void setSaleSort(Integer saleSort) {
        this.saleSort = saleSort;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
