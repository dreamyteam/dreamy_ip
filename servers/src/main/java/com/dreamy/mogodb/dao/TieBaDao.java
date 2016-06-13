package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/6/12.
 */
@Repository("tieBaDao")
public class TieBaDao extends MongoGenDao<TieBa> {
    @Override
    protected Class<TieBa> getEntityClass() {
        return TieBa.class;
    }


    public void updateInser(TieBa tieBa) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(tieBa);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(tieBa.getBookId())), update);
    }
}
