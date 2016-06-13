package com.dreamy.mogodb.beans.history;

import org.springframework.data.annotation.Id;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class NewsMediaHistory {
    @Id
    private String id;

    private Integer bookId;

    private Integer num;

    private String createDate;

    private Integer source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
