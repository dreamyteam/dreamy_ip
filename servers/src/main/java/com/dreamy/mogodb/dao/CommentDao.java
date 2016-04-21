package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.Comments;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyongxing on 16/4/18.
 */
@Repository("commentDao")
public class CommentDao extends MongoGenDao<Comments> {
    @Override
    protected Class<Comments> getEntityClass() {
        return Comments.class;
    }
}
