package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/4/25.
 */
@Repository("bookIndexDataDao")
public class BookIndexDataDao extends MongoGenDao<BookIndexData> {

    protected Class<BookIndexData> getEntityClass() {
        return BookIndexData.class;
    }


    public void updateInser(BookIndexData data) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(data);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(data.getId())), update);
    }

    public BookIndexData queryById(String id) {

        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        return this.mongoTemplate.findOne(query, getEntityClass());
    }
}
