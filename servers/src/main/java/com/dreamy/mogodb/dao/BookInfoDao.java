package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookInfo;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/5.
 */
@Repository("bookInfoDao")
public class BookInfoDao extends MongoGenDao<BookInfo> {


    protected Class<BookInfo> getEntityClass() {
        return BookInfo.class;
    }


    public DBObject getList() {
    BasicDBObject groupOpt = new BasicDBObject();
    Map groupMap = new HashMap();
    groupOpt.put("$group", new BasicDBObject(groupMap));

    AggregationOutput aggrResult = super.mongoTemplate.getCollection("bookInfo").aggregate(null, groupOpt);
    Iterator<DBObject> iter = aggrResult.results().iterator();
    DBObject result = null;
    while (iter.hasNext()) {
        result = iter.next();
        break;
    }
    return result;
}
    }


