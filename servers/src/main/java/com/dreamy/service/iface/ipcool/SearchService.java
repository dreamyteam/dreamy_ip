package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/8
 * Time: 下午6:54
 */
public interface SearchService {

    /**
     *
     * @param name
     * @param page
     * @return
     */
    List<Integer> getBookIdsFromSolrByNameAndType(String name, Page page,Integer type);
}
