package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.history.BookIndexDataHistory;
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
@Repository("bookIndexDataHistoryDao")
public class BookIndexDataHistoryDao extends MongoGenDao<BookIndexDataHistory> {
    @Override
    protected Class<BookIndexDataHistory> getEntityClass() {
        return BookIndexDataHistory.class;
    }

    public void updateInser(BookIndexDataHistory info) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(info);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(info.getId())), update);
    }

    public List<BookIndexDataHistory> queryByBookId(Integer bookId){

        Query query = new Query();
        Criteria criteria = Criteria.where("bookId").is(bookId);
        query.addCriteria(criteria);
        return  this.queryList(query);
    }
}
