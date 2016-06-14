package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/28.
 */
public interface BookScoreService {
    /**
     * @param bookScore
     */
    public void saveUpdate(BookScore bookScore);

    /**
     * @param bookScore
     * @param page
     * @return
     */
    public List<BookScore> getList(BookScore bookScore, Page page);

    /**
     * @param bookId
     * @return
     */
    public List<BookScore> getByBookId(Integer bookId);

    /**
     * @param bookId
     * @param options
     * @return
     */
    public String getBookHotIndexByBookId(Integer bookId);

    /**
     * @param bookId
     * @return
     */
    public String getPropagateIndexByBookId(Integer bookId);


    /**
     * @param bookId
     * @return
     */
    public String getDevelopIndexByRecord(BookView bookView);

    /**
     * @param bookId
     * @return
     */
    public String getReputationIndexByBookId(Integer bookId);

    /**
     *
     * @param bookId
     * @return
     */
    Double getSearchIndexByBookId(Integer bookId);



}
