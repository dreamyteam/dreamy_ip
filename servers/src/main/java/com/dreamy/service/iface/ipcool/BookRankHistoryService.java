package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRankHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookRankHistoryService {

    /**
     *
     * @param bookRankHistory
     */
    public void save(BookRankHistory bookRankHistory);

    /**
     *
     * @param bookRankHistory
     * @param page
     * @return
     */
    public List<BookRankHistory> getList(BookRankHistory bookRankHistory, Page page);

    /**
     *
     * @param bookId
     * @return
     */
    List<BookRankHistory> getByBookIdAndType(Integer bookId,Integer type);


}
