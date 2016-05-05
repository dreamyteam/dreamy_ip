package com.dreamy.service.iface.ipcool;

import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsMap;

import java.util.List;
import java.util.Map;

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
     * @param bookTagsMap
     * @return
     */
    Integer saveTagMap(BookTagsMap bookTagsMap);

    /**
     * @param name
     * @return
     */
    List<BookTags> getByName(String name);

    /**
     * @param bookId
     * @return
     */
    Map<Integer, String> getTagMapByBookId(Integer bookId);


}
