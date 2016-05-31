package com.dreamy.admin.thread;

import com.dreamy.domain.ipcool.BookTags;
import com.dreamy.domain.ipcool.BookTagsMap;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookTagsService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangyongxing on 16/5/17.
 */
@Service
public class ExtractBookViewService {
    private static final Logger log = LoggerFactory.getLogger(ExtractBookViewService.class);

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookTagsService bookTagsService;
    @Autowired
    private BookViewService bookViewService;

    public void extract(List<IpBook> list) {

        for (IpBook ipBook : list) {
            String id = ipBook.getCode() + "_" + CrawlerSourceEnums.douban.getType();
            BookInfo bookInfo = bookInfoService.getById(id);
            BookView bookView = new BookView();
            bookView.bookId(ipBook.getId());
            bookView.name(ipBook.getName());
            bookView.author(bookInfo.getAuthor());
            bookView.setType(1);
            bookView.status(1);
            bookView.imageUrl(bookInfo.getImage());
            bookView.introduction(bookInfo.getInfo());
            bookViewService.save(bookView);
            tagAnalysis(ipBook.getCode(), ipBook.getId());
        }

    }

    /**
     * tags 目前只取当当的值
     *
     * @param isbn
     */
    private void tagAnalysis(String isbn, int bookId) {
        String id = isbn + "_" + CrawlerSourceEnums.dangdang.getType();
        BookInfo bookInfo = bookInfoService.getById(id);
        if (bookInfo != null) {
            List<String> tagsList = getAllTags(bookInfo.getCategories());
            if (CollectionUtils.isNotEmpty(tagsList)) {
                for (String tagName : tagsList) {
                    List<BookTags> bookTagsList = bookTagsService.getByName(tagName);
                    Integer tagId = 0;
                    if (CollectionUtils.isEmpty(bookTagsList)) {
                        BookTags bookTags = new BookTags();
                        bookTags.name(tagName);
                        bookTagsService.save(bookTags);
                        tagId = bookTags.getId();
                    } else {
                        tagId = bookTagsList.get(0).getId();
                    }
                    BookTagsMap bookTagsMap = new BookTagsMap();
                    bookTagsMap.bookId(bookId).tagId(tagId);

                    bookTagsService.saveTagMap(bookTagsMap);
                }
            }
        }


    }

    /**
     * 获取所有的标签的一个list
     *
     * @param tags
     * @return
     */
    private List<String> getAllTags(String tags) {

        List<String> list = new LinkedList<>();
        if (StringUtils.isNotEmpty(tags)) {
            String[] tagsArray = tags.split(",");
            Integer arrayLength = tagsArray.length;

            for (int i = 0; i < arrayLength; i++) {
                String[] tagStrArray = tagsArray[i].split(">");

                try {
                    String tagName = tagStrArray[tagStrArray.length - 1];
                    if (tagName.contains("/")) {
                        String[] tagsTemp = tagName.split("/");
                        for (int j = 0, tagsTempLength = tagsTemp.length; j < tagsTempLength; j++) {
                            list.add(tagsTemp[j]);
                        }
                    } else {
                        list.add(tagName);
                    }
                } catch (Exception e) {
                    log.error("get all tags failed " + JsonUtils.toString(tagStrArray), e);
                }
            }
        }
        return list;
    }
}
