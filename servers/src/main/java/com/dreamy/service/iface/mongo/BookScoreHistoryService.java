package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.history.BookScoreHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public interface BookScoreHistoryService {

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     *
     * @param bookScoreHistory
     */
    public void updateInser(BookScoreHistory bookScoreHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param bookScoreHistory
     */
    void saveByRecord(BookScoreHistory bookScoreHistory);


    public List<BookScoreHistory> getByBookId(Integer bookId);
}
