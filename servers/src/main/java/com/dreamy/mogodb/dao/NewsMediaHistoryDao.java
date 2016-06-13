package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.NewsMediaHistory;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Repository("newsMediaHistoryDao")
public class NewsMediaHistoryDao extends MongoGenDao<NewsMediaHistory> {
    @Override
    protected Class<NewsMediaHistory> getEntityClass() {
        return NewsMediaHistory.class;
    }

    public void updateInser(NewsMediaHistory info) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(info);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(info.getId())), update);
    }

    public List<NewsMediaHistory> queryByBookId(Integer bookId){

        Query query = new Query();
        Criteria criteria = Criteria.where("bookId").is(bookId);
        query.addCriteria(criteria);
        return  this.queryList(query);
    }
}
