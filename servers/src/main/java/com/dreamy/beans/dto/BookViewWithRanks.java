package com.dreamy.beans.dto;

import com.dreamy.domain.ipcool.BookView;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/6
 * Time: 上午10:02
 */
public class BookViewWithRanks {
    private BookView bookView;

    private Integer compositeRank;


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
}
