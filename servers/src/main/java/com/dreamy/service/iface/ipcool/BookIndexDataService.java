package com.dreamy.service.iface.ipcool;

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
}
