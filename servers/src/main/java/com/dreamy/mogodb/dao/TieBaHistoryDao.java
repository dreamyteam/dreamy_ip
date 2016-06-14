package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.history.TieBaHistory;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/12.
 */
@Repository("tieBaHistoryDao")
public class TieBaHistoryDao extends MongoGenDao<TieBaHistory> {
    @Override
    protected Class<TieBaHistory> getEntityClass() {
        return TieBaHistory.class;
    }


    public void updateInser(TieBaHistory tieBaHistory) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(tieBaHistory);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(tieBaHistory.getId())), update);
    }

    public List<TieBaHistory> queryByBookId(Integer bookId) {

        Query query = new Query();
        Criteria criteria = Criteria.where("bookId").is(bookId);
        query.addCriteria(criteria);
        return this.queryList(query);
    }

    public TieBaHistory queryById(String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        return this.queryOne(query);
    }


}
