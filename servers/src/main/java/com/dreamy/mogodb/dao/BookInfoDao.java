package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.BeanUtils;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    public void updateMulti(BookInfo bookInfo) {
        Update update = new Update();
//        if (null == bookInfo.getCrawlerId()) {
//            //如果主键为空,则不进行修改
//            throw new Exception("Update data Id is " +
//                    "Null");
//        }
        Map<String, Object> map = BeanUtils.toMongodbMap(bookInfo);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(!entry.getKey().equals("ISBN")) {
                update.set(entry.getKey(), entry.getValue());
            }
        }

        this.updateMulti(Query.query(Criteria.where("ISBN").is(bookInfo.getISBN())), update);
    }

    public void updateInser(BookInfo bookInfo) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(bookInfo);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(bookInfo.getId())), update);
    }

    public BookInfo getByStringId(String id){
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        return super.mongoTemplate.findOne(query, this.getEntityClass());

    }


}


