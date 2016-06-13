package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.BookIndexDataHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public interface BookIndexDataHistoryService {


    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param bookIndexDataHistory
     */
    public void updateInser(BookIndexDataHistory bookIndexDataHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param bookIndexDataHistory
     */
    void saveByRecord(BookIndexDataHistory bookIndexDataHistory);


    public List<BookIndexDataHistory> getByBookId(Integer id) ;
}
