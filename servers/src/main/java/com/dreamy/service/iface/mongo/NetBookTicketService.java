package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.NetBookTicket;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by wangyongxing on 16/6/7.
 */
public interface NetBookTicketService {


    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param netBookTicket
     */
    public void updateInser(NetBookTicket netBookTicket);

    /**
     * 保存一个netBookTicket到mongo
     *
     * @param netBookTicket
     */
    void saveByRecord(NetBookTicket netBookTicket);


    public NetBookTicket getById(Integer code) ;

}
