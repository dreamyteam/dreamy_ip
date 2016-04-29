package com.dreamy.service.iface.ipcool;

import com.dreamy.domain.ipcool.BookIndexTaskLog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/29
 * Time: 下午12:29
 */
public interface BookIndexTaskLogService {

    /**
     * 获取所有的记录 其实也就4条
     *
     * @return
     */
    List<BookIndexTaskLog> getAllByStatus(Integer status);

    /**
     * @param indexType
     * @return
     */
    BookIndexTaskLog getByIndexType(Integer indexType);

    /**
     * @param indexType
     * @return
     */
    Boolean isTaskActive(Integer indexType);

    /**
     *
     * @param bookIndexTaskLog
     */
    void update(BookIndexTaskLog bookIndexTaskLog);



}
