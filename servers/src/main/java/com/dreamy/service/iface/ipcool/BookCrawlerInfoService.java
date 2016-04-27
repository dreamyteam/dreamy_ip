package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookCrawlerInfoConditions;
import com.dreamy.domain.ipcool.IpBook;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/11.
 */
public interface BookCrawlerInfoService {

    /**
     * @param info
     * @return
     */
    BookCrawlerInfo save(BookCrawlerInfo info);

    /**
     * @param bookCrawlerInfo
     * @return
     */
    List<BookCrawlerInfo> getByRecord(BookCrawlerInfo bookCrawlerInfo);

    /**
     * @param bookCrawlerInfo
     * @param page
     * @return
     */
    List<BookCrawlerInfo> getListByRecord(BookCrawlerInfo bookCrawlerInfo, Page page);


    /**
     * @param id
     * @return
     */
    BookCrawlerInfo getById(Integer id);

    /**
     * @param info
     * @return
     */
    int update(BookCrawlerInfo info);

    /**
     * @param page
     * @return
     */
    List<BookCrawlerInfo> getByPageAndOrder(Page page, String order);


    /**
     * @param conditions
     * @param page
     * @return
     */
    List<BookCrawlerInfo> getByCondition(BookCrawlerInfoConditions conditions);
}
