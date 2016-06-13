package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.history.NetBookDataHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public interface NetBookDataHistoryService {


    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     *
     * @param netBookDataHistory
     */
    public void updateInser(NetBookDataHistory netBookDataHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param netBookDataHistory
     */
    void saveByRecord(NetBookDataHistory netBookDataHistory);


    public List<NetBookDataHistory> getByBookId(Integer bookId);
}
