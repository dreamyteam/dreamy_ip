package com.dreamy.mogodb.beans.history;

import org.springframework.data.annotation.Id;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class KeyWordHistory {
    @Id
    private String id;

    private Integer bookId;

    private Integer num;

    private String createDate;

    private Integer source;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
