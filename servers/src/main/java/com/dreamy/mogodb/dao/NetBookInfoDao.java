package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/5/31.
 */
@Repository("netBookInfoDao")
public class NetBookInfoDao extends MongoGenDao<NetBookInfo>  {

    protected Class<NetBookInfo> getEntityClass() {
        return NetBookInfo.class;
    }


    public void updateInser(NetBookInfo info) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(info);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(info.getBookId())), update);
    }

    public NetBookInfo queryById(String id) {

        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        return this.mongoTemplate.findOne(query, getEntityClass());
    }
}
