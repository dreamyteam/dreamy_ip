package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/11.
 */
public interface IpBookService {

    /**
     * @param ipBook
     * @param list
     * @return
     */
    IpBook saveRecordAndCrawlerInfo(IpBook ipBook, List<BookCrawlerInfo> list);

    /**
     * @param id
     * @return
     */
    IpBook getById(Integer id);

    /**
     * @param ipBook
     * @param page
     * @return
     */
    List<IpBook> getIpBookList(IpBook ipBook, Page page);

    /**
     * @param ipBook
     * @param list
     * @return
     */
    int updateRecordAndCrawlerInfo(IpBook ipBook, List<BookCrawlerInfo> list);

    /**
     *
     * @param ipBook
     * @return
     */
    Integer updateByRecord(IpBook ipBook);

    /**
     * @param ids
     * @return
     */
    int delByIds(List<Integer> ids);

    /**
     *
     * @param bookCrawlerInfo
     */
    void doCrawler(BookCrawlerInfo bookCrawlerInfo);

    void save(IpBook ipBook);
}
