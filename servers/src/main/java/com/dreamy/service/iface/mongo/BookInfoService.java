package com.dreamy.service.iface.mongo;

import com.dreamy.beans.Page;
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


    /**
     *
     * @param ipId
     * @return
     */
    public List<BookInfo> getListByIpId(int ipId);

    /**
     *
     * @param page
     * @param order
     * @return
     */
    public List<BookInfo> getListByOrderAndPage(Page page,String order);

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param bookInfo
     */
    public void updateInser(BookInfo bookInfo);
 

}
