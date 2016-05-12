package com.dreamy.service.impl.ipcool;

import com.dreamy.dao.iface.ipcool.BookTagsDao;
import com.dreamy.dao.iface.ipcool.BookTagsMapDao;
import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsConditions;
import com.dreamy.domain.ipcool.BookTagsMap;
import com.dreamy.domain.ipcool.BookTagsMapConditions;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.service.iface.ipcool.BookTagsService;

import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/4
 * Time: 下午5:51
 */
@Service
public class BookTagsServiceImpl implements BookTagsService {

    @Autowired
    private BookTagsDao bookTagsDao;

    @Autowired
    private BookTagsMapDao bookTagsMapDao;

    @Override
    public Integer save(BookTags bookTags) {
        return bookTagsDao.save(bookTags);
    }

    @Override
    public List<BookTags> getByName(String name) {

        BookTagsConditions conditions = new BookTagsConditions();
        conditions.createCriteria().andNameEqualTo(name);

        return bookTagsDao.selectByExample(conditions);
    }

    @Override
    public Map<Integer, String> getTagMapByBookId(Integer bookId) {

        Map<Integer, String> tagIdMap = new HashMap<>();

        BookTagsMapConditions mapConditions = new BookTagsMapConditions();
        mapConditions.createCriteria().andBookIdEqualTo(bookId);
        List<BookTagsMap> bookTagsMapList = bookTagsMapDao.selectByExample(mapConditions);
        if (CollectionUtils.isNotEmpty(bookTagsMapList)) {

            List<Integer> tagIds = new LinkedList<>();
            for (BookTagsMap bookTagsMap : bookTagsMapList) {
                tagIds.add(bookTagsMap.getTagId());
            }

            BookTagsConditions conditions = new BookTagsConditions();
            conditions.createCriteria().andIdIn(tagIds);

            List<BookTags> bookTagsList = bookTagsDao.selectByExample(conditions);
            if (CollectionUtils.isNotEmpty(bookTagsList)) {
                for (BookTags tag : bookTagsList)
                    tagIdMap.put(tag.getId(), tag.getName());
            }

        }

        return tagIdMap;
    }

    @Override
    public Integer saveTagMap(BookTagsMap bookTagsMap) {
        return bookTagsMapDao.save(bookTagsMap);
    }
}
