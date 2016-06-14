package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.history.BookIndexDataHistory;
import com.dreamy.mogodb.beans.history.BookScoreHistory;
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
@Repository("bookScoreHistoryDao")
public class BookScoreHistoryDao extends MongoGenDao<BookScoreHistory> {
    @Override
    protected Class<BookScoreHistory> getEntityClass() {
        return BookScoreHistory.class;
    }

    public void updateInser(BookScoreHistory bookScoreHistory) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(bookScoreHistory);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(bookScoreHistory.getId())), update);
    }

    public List<BookScoreHistory> queryByBookId(Integer bookId){

        Query query = new Query();
        Criteria criteria = Criteria.where("bookId").is(bookId);
        query.addCriteria(criteria);
        return  this.queryList(query);
    }
}
