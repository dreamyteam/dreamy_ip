package com.dreamy.beans.dto;

import com.dreamy.domain.ipcool.BookView;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/6
 * Time: 上午10:02
 */
public class BookViewWithExt {
    private BookView bookView;

    private Integer compositeRank;

    //0 表示持平 1 增长 2 降低
    private Integer trend = 0;


    public BookView getBookView() {
        return bookView;
    }

    public void setBookView(BookView bookView) {
        this.bookView = bookView;
    }

    public Integer getCompositeRank() {
        return compositeRank;
    }

    public void setCompositeRank(Integer compositeRank) {
        this.compositeRank = compositeRank;
    }

    public Integer getTrend() {
        return trend;
    }

    public void setTrend(Integer trend) {
        this.trend = trend;
    }
}
