package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookCrawlerInfoConditions;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
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

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Scheduled(fixedDelay = 8000)
    private void run() {
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

                    BookView oldBookView = bookViewService.getByBookId(currentId);
                    if (oldBookView.getId() == null) {
                        BookView bookView = getBookViewByBookId(currentId);
                        bookView.setType(1);
                        bookView.setStatus(0);
                        bookViewService.save(bookView);
                    }
                }
            }
        }

    }

    public BookView getBookViewByBookId(Integer bookId) {
        BookView bookView = new BookView();
        List<BookInfo> bookInfos = bookInfoService.getListByIpId(bookId);
        if (CollectionUtils.isNotEmpty(bookInfos)) {

            Map<Integer, BookInfo> bookInfoMap = new HashMap<>();
            for (BookInfo bookInfo : bookInfos) {
                bookInfoMap.put(bookInfo.getSource(), bookInfo);
            }

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

            bookView.bookId(bookId)
                    .name(getRightData(nameList))
                    .author(getRightData(authorList))
                    .introduction(getRightData(introList))
                    .imageUrl(getRightData(imageList));


        }


        return bookView;
    }

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
