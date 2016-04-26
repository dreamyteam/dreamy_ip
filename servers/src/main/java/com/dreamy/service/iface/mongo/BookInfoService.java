package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.BookInfo;

import java.util.List;

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
    void saveByRecord(BookInfo bookInfo);

    /**
     * @param id
     * @return
     */
    BookInfo getById(Integer id);


    /**
     *
     * @param id
     */
    void delById(Integer id);


    List<BookInfo> getList();


}
