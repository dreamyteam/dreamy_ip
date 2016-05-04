package com.dreamy.service.iface.ipcool;

import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsMap;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午5:51
 */
public interface BookTagsService {
    /**
     * @param bookTags
     */
    Integer save(BookTags bookTags);

    /**
     * @param name
     * @return
     */
    List<BookTags> queryByName(String name);

    /**
     * @param bookTagsMap
     * @return
     */
    Integer saveTagMap(BookTagsMap bookTagsMap);


}
