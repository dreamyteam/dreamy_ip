package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/11.
 */
public interface BookCrawlerInfoService {

    BookCrawlerInfo save(BookCrawlerInfo info);

    List<BookCrawlerInfo> getBy(BookCrawlerInfo bookCrawlerInfo);

    List<BookCrawlerInfo> getList(BookCrawlerInfo bookCrawlerInfo, Page page);

    int update(BookCrawlerInfo info);
}
