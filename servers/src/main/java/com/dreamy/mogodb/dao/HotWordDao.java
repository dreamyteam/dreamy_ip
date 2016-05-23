package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.HotWord;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyongxing on 16/5/20.
 */
@Repository("hotWordDao")
public class HotWordDao extends MongoGenDao<HotWord> {
    @Override
    protected Class<HotWord> getEntityClass() {
        return HotWord.class;
    }
}
