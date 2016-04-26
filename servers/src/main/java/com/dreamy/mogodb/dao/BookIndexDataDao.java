package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookIndexData;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyongxing on 16/4/25.
 */
@Repository("bookIndexDataDao")
public class BookIndexDataDao extends MongoGenDao<BookIndexData> {

    protected Class<BookIndexData> getEntityClass() {
        return BookIndexData.class;
    }
}
