package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.beans.NetBookTicket;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/6/7.
 */
@Repository("netBookTicketDao")
public class NetBookTicketDao extends MongoGenDao<NetBookTicket>  {

    protected Class<NetBookTicket> getEntityClass() {
        return NetBookTicket.class;
    }


    public void updateInser(NetBookTicket netBookTicket) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(netBookTicket);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(netBookTicket.getCode())), update);
    }

}
