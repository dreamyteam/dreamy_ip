package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.tieba.TieBa;

/**
 * Created by wangyongxing on 16/6/12.
 */
public interface TieBaService {
    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param tieBa
     */
    public void updateInser(TieBa tieBa);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param tieBa
     */
    void saveByRecord(TieBa tieBa);


    public TieBa getById(Integer bookId) ;
}
