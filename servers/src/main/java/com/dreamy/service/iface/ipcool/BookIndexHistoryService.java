package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookIndexHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookIndexHistoryService {

    /**
     * @param bookIndexHistory
     */
    public void save(BookIndexHistory bookIndexHistory);

    /**
     * @param bookIndexHistory
     * @param page
     * @param orderBy
     * @return
     */
    public List<BookIndexHistory> getList(BookIndexHistory bookIndexHistory, Page page, String orderBy);

    /**
     * 获取指数最高纪录
     *
     * @param bookId
     */
    public BookIndexHistory getMaxByBookId(Integer bookId);

    /**
     * @param date
     * @return
     */
    public Integer delByDate(Date date);


    /**
     *
     * @param bookId
     * @param date
     * @return
     */

    BookIndexHistory getByBookIdAndDate(Integer bookId, Date date);


    /**
     *
     * @param bookId
     * @param date
     * @return
     */
    public Integer delByBookIdAndDate(Integer bookId,Date date);

}
