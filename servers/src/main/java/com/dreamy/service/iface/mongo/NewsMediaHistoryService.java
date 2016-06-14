package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.history.NewsMediaHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
public interface NewsMediaHistoryService {


    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param newsMediaHistory
     */
    public void updateInser(NewsMediaHistory newsMediaHistory);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param newsMediaHistory
     */
    void saveByRecord(NewsMediaHistory newsMediaHistory);


    public List<NewsMediaHistory> getByBookId(Integer id) ;
}
