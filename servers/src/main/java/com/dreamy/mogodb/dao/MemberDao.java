package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.Member;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/5.
 */
@Repository("memberDao")
public class MemberDao extends MongoGenDao<Book> {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }


    public List<Book> queryPage(Book member, Integer start, Integer size) {
        Query query = new Query();
        //此处可以增加分页查询条件Criteria.然后query.addCriteria(criteria);
        return this.getPage(query, (start - 1) * size, size);
    }
}
