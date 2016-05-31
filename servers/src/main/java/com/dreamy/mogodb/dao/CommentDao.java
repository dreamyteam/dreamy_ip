package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.Comments;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/4/18.
 */
@Repository("commentDao")
public class CommentDao extends MongoGenDao<Comments> {
    @Override
    protected Class<Comments> getEntityClass() {
        return Comments.class;
    }

    public void updateInser(Comments comments) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(comments);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(comments.getIpId())), update);
    }


}
