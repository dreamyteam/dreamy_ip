package com.dreamy.admin.beans;

import com.dreamy.domain.ipcool.BookCrawlerInfo;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/12.
 */
public class BookCrawlerModel {
    private List<BookCrawlerInfo> infos;

    public List<BookCrawlerInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<BookCrawlerInfo> infos) {
        this.infos = infos;
    }
}
