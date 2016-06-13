package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.history.KeyWordHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public interface KeyWordHistoryService {


    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param keyWordHistory
     */
    public void updateInser(KeyWordHistory keyWordHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param keyWordHistory
     */
    void saveByRecord(KeyWordHistory keyWordHistory);


    public List<KeyWordHistory> getByBookId(Integer id) ;
}
