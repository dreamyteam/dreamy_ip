package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.qidian.QiDianFan;

/**
 * Created by wangyongxing on 16/6/8.
 */
public interface QiDianFanService {

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param qianDainFan
     */
    public void updateInser(QiDianFan qianDainFan);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param qianDainFan
     */
    void saveByRecord(QiDianFan qianDainFan);


    public QiDianFan getById(Integer bookId) ;
}
