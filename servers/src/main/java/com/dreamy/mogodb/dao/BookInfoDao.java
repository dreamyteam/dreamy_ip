package com.dreamy.mogodb.dao;

import com.dreamy.mogodb.beans.BookInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by wangyongxing on 16/4/5.
 */
@Repository("bookInfoDao")
public class BookInfoDao extends MongoGenDao<BookInfo> {
    protected Class<BookInfo> getEntityClass() {
        return BookInfo.class;
    }
}
