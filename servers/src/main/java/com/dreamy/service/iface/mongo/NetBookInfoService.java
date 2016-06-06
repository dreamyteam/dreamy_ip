package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.NetBookInfo;

/**
 * Created by wangyongxing on 16/6/1.
 */
public interface NetBookInfoService {

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param netBookInfo
     */
    public void updateInser(NetBookInfo netBookInfo);

    /**
     * 保存一个netBookInfo到mongo
     *
     * @param netBookInfo
     */
    void saveByRecord(NetBookInfo netBookInfo);

    NetBookInfo getById(Integer bookId);
}
