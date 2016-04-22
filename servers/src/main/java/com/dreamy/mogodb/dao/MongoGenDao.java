package com.dreamy.mogodb.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/5.
 */
public abstract class  MongoGenDao<T> {
    private static final Logger log = LoggerFactory.getLogger(MongoGenDao.class);


    @Autowired
    protected MongoTemplate mongoTemplate;

    public void save(T t){
        this.mongoTemplate.save(t);
    }


    /**
     * 根据Id从Collection中查询对象
     * @param id
     * @return
     */
    public T queryById(Integer id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        log.info("[Mongo Dao ]queryById:" + query);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据条件查询集合
     * @param query
     * @return
     */
    public List<T> queryList(Query query){
        log.info("[Mongo Dao ]queryList:" + query);
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     *   通过条件查询单个实体
     * @param query
     * @return
     */
    public T queryOne(Query query){
        log.info("[Mongo Dao ]queryOne:" + query);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }
    /**
     * 通过条件进行分页查询

     * @param query
     *
     * @param start
     * 查询起始值
     * 类似mysql查询中的 limit start, size 中的 start
     * @param size
     * 查询大小
     * 类似mysql查询中的 limit start, size 中的 size
     * @return
     */
    public List<T> getPage(Query query, int start, int size){
        query.skip(start);
        query.limit(size);
        log.info("[Mongo Dao ]queryPage:" + query + "(" + start +"," + size +")");
        List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
        return lists;
    }

    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务
     *
     *
     * @param query
     * @return
     */
    public Long getPageCount(Query query){
        log.info("[Mongo Dao ]queryPageCount:" + query);
        return this.mongoTemplate.count(query, this.getEntityClass());
    }
    public void deleteById(Integer id) {
        Criteria criteria = Criteria.where("_id").in(id);
        if(null!=criteria){
            Query query = new Query(criteria);
            log.info("[Mongo Dao ]deleteById:" + query);
            if(null!=query && this.queryOne(query)!=null){
                this.delete((T)query);
            }
        }


    }
    public void delete(T t){
        log.info("[Mongo Dao ]delete:" + t);
        this.mongoTemplate.remove(t);
    }
    /**
     * 更新满足条件的第一个记录
     * @param query
     * @param update
     */
    public void updateFirst(Query query,Update update){
        log.info("[Mongo Dao ]updateFirst:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    /**
     * 更新满足条件的所有记录
     * @param query
     * @param update
     */
    public void updateMulti(Query query, Update update){
        log.info("[Mongo Dao ]updateMulti:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     *
     * @param query
     * @param update
     */
    public void updateInser(Query query, Update update){
        log.info("[Mongo Dao ]updateInser:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.upsert(query, update, this.getEntityClass());
    }


    protected abstract Class<T> getEntityClass();
}
