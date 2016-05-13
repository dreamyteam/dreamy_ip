package com.dreamy.admin.tasks;

import com.dreamy.domain.ipcool.*;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookTagsService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/27
 * Time: 上午10:28
 */
@Component
public class BookViewCreateTask {
    private static final Logger log = LoggerFactory.getLogger(BookViewCreateTask.class);

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private BookTagsService bookTagsService;

    @Scheduled(fixedDelay = 8000)
    public void run() {
        try {
            BookCrawlerInfoConditions conditions = new BookCrawlerInfoConditions();
            conditions.createCriteria().andStatusEqualTo(CrawlerTaskStatusEnums.success.getStatus());
            conditions.setOrderByClause("book_id asc");
            List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getByCondition(conditions);
            if (CollectionUtils.isNotEmpty(bookCrawlerInfos)) {

                Integer initBookId = 0;
                for (BookCrawlerInfo bookCrawlerInfo : bookCrawlerInfos) {
                    Integer currentId = bookCrawlerInfo.getBookId();
                    if (!currentId.equals(initBookId)) {
                        initBookId = currentId;

                        List<BookInfo> bookInfos = bookInfoService.getListByIpId(currentId);
                        if (CollectionUtils.isNotEmpty(bookInfos)) {
                            Map<Integer, BookInfo> bookInfoMap = new HashMap<>();
                            for (BookInfo bookInfo : bookInfos) {
                                bookInfoMap.put(bookInfo.getSource(), bookInfo);
                            }

                            BookView oldBookView = bookViewService.getByBookId(currentId);
                            if (oldBookView == null) {
                                BookView bookView = getBookViewByBookId(bookInfoMap);
                                bookView.setType(1);
                                bookView.setStatus(0);
                                bookView.bookId(currentId);
                                bookViewService.save(bookView);

                                tagAnalysis(bookInfoMap);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("created view task failed", e);
        }

    }

    /**
     * 获取要插入的数据
     *
     * @param bookInfoMap
     * @return
     */
    public BookView getBookViewByBookId(Map<Integer, BookInfo> bookInfoMap) {
        BookView bookView = new BookView();

        //设定顺序
        List<Integer> crawlerSources = new LinkedList<>();
        crawlerSources.add(CrawlerSourceEnums.douban.getType());
        crawlerSources.add(CrawlerSourceEnums.dangdang.getType());
        crawlerSources.add(CrawlerSourceEnums.jd.getType());
        crawlerSources.add(CrawlerSourceEnums.amazon.getType());


        List<String> nameList = new LinkedList<>();
        List<String> authorList = new LinkedList<>();
        List<String> introList = new LinkedList<>();
        List<String> imageList = new LinkedList<>();

        for (Integer crawlerSource : crawlerSources) {
            if (bookInfoMap.containsKey(crawlerSource)) {
                BookInfo currentBookInfo = bookInfoMap.get(crawlerSource);
                nameList.add(currentBookInfo.getTitle());
                authorList.add(currentBookInfo.getAuthor());
                introList.add(currentBookInfo.getInfo());
                imageList.add(currentBookInfo.getImage());
            }
        }

        bookView.name(getRightData(nameList))
                .author(getRightData(authorList))
                .introduction(getRightData(introList))
                .imageUrl(getRightData(imageList));


        return bookView;
    }

    /**
     * tags 目前只取当当的值
     *
     * @param bookInfoMap
     */
    private void tagAnalysis(Map<Integer, BookInfo> bookInfoMap) {
        if (bookInfoMap.containsKey(CrawlerSourceEnums.dangdang.getType())) {
            BookInfo dangdangData = bookInfoMap.get(CrawlerSourceEnums.dangdang.getType());
            String tags = dangdangData.getCategories();

            List<String> tagsList = getAllTags(tags);
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
                    bookTagsMap.bookId(dangdangData.getIpId()).tagId(tagId);

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

    /**
     * 获取最前面的一个值
     *
     * @param dataList
     * @return
     */
    private String getRightData(List<String> dataList) {
        String res = "";
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (String data : dataList) {
                if (StringUtils.isNotEmpty(data)) {
                    res = data;
                    break;
                }
            }
        }

        return res;
    }
}
