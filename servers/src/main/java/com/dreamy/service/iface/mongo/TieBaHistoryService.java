package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.history.TieBaHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/12.
 */
public interface TieBaHistoryService {
    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     *
     * @param tieBaHistory
     */
    public void updateInser(TieBaHistory tieBaHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param tieBaHistory
     */
    void saveByRecord(TieBaHistory tieBaHistory);
 
    /**
     * @param id
     * @return
     */
    public List<TieBaHistory> getByBookId(Integer id);

    /**
     * @param bookId
     * @return
     */
    TieBaHistory getLatestHistoryByBookId(Integer bookId);
}
