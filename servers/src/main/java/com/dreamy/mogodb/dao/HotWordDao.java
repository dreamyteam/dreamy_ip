package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/5/20.
 */
@Repository("hotWordDao")
public class HotWordDao extends MongoGenDao<HotWord> {
    @Override
    protected Class<HotWord> getEntityClass() {
        return HotWord.class;
    }


    public void updateInser(HotWord hotWord) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(hotWord);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(hotWord.getId())), update);
    }
}
