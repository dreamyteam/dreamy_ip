package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.BookInfo;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/21
 * Time: 上午11:57
 */
public interface BookInfoService {


    /**
     * 保存一个bookinfo到mongo
     *
     * @param bookInfo
     */
    void add(BookInfo bookInfo);

    /**
     * @param id
     * @return
     */
    BookInfo queryById(Integer id);


}
