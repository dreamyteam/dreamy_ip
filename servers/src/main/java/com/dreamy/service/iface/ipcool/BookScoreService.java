package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.mogodb.beans.Book;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */
public interface BookScoreService {
    /**
     * @param bookScore
     */
    public void save(BookScore bookScore);

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
    public String getDevelopIndexByBookId(Integer bookId);

    /**
     * @param bookId
     * @return
     */
    public String getReputationIndexByBookId(Integer bookId);

}
