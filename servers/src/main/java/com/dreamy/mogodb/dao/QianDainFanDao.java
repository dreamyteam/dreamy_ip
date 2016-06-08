package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.qidian.QiDianFan;
import com.dreamy.utils.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangyongxing on 16/6/8.
 */
@Repository("qianDainFanDao")
public class QianDainFanDao extends MongoGenDao<QiDianFan>  {

    protected Class<QiDianFan> getEntityClass() {
        return QiDianFan.class;
    }


    public void updateInser(QiDianFan qianDainFan) {
        Update update = new Update();
        Map<String, Object> map = BeanUtils.toMongodbMap(qianDainFan);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        this.updateInser(Query.query(Criteria.where("_id").is(qianDainFan.getBookId())), update);
    }
}
