package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.mogodb.beans.tieba.TieBaHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/12.
 */
public interface TieBaHistoryService {
    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param tieBaHistory
     */
    public void updateInser(TieBaHistory tieBaHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param tieBaHistory
     */
    void saveByRecord(TieBaHistory tieBaHistory);


    public List<TieBaHistory> getByBookId(Integer id) ;
}
