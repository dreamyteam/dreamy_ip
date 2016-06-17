package com.dreamy.service.iface.mongo;

import com.dreamy.mogodb.beans.BookIndexData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/3
 * Time: 下午2:39
 */
public interface BookIndexDataService {

    /**
     * @param bookId
     * @return
     */
    List<BookIndexData> getByBookId(Integer bookId);

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param data
     */
    public void updateInser(BookIndexData data);


    /**
     *
     * @param id
     * @return
     */
    public BookIndexData getById(String id);

    /**
     *
     * @param id
     * @return
     */
    public BookIndexData queryById(Integer id);
}
