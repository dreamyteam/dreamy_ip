package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.enums.IpBookStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.QueueRoutingKey;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/21
 * Time: 下午4:58
 */
@Component
public class CreateCrawlerTask {
    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Resource
    private IpBookService ipBookService;

    @Autowired
    private BookInfoService bookInfoService;

    @Scheduled(fixedDelay = 5000)
    public void checkBookCrawlerWaittingStatus() {
        Page page = new Page();
        page.setPageSize(1000);

        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().status(CrawlerTaskStatusEnums.waitting.getStatus());
        List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getListByRecord(bookCrawlerInfo, page);
        if (CollectionUtils.isNotEmpty(bookCrawlerInfos)) {
            for (BookCrawlerInfo info : bookCrawlerInfos) {
                ipBookService.doCrawler(info);
            }
        }
    }

    @Scheduled(fixedDelay = 6000)
    public void checkBookCrawlerFinishedStatus() {
        Page page = new Page();
        page.setPageSize(1000);

        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().status(CrawlerTaskStatusEnums.starting.getStatus());
        List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getListByRecord(bookCrawlerInfo, page);
        if (CollectionUtils.isEmpty(bookCrawlerInfos)) {
            return;
        }
        for (BookCrawlerInfo info : bookCrawlerInfos) {
            if (StringUtils.isNotEmpty(info.getUrl())) {
                BookInfo bookInfo = bookInfoService.getById(info.getId());
                if (bookInfo == null) {
                    continue;
                } else {
                    info.status(CrawlerTaskStatusEnums.finished.getStatus());
                    bookCrawlerInfoService.update(info);
                }
            }
        }

    }


    @Scheduled(fixedDelay = 7000)
    public void checkIpBookStatus() {
        Page page = new Page();
        page.setPageSize(1000);

        IpBook ipBook = new IpBook().status(IpBookStatusEnums.waitting.getStatus());
        List<IpBook> ipBooks = ipBookService.getIpBookList(ipBook, page);
        if (CollectionUtils.isEmpty(ipBooks)) {
            return;
        }

        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
        Page bookCrawlerPage = new Page();
        for (IpBook book : ipBooks) {
            Integer bookStatus = book.getStatus();

            bookCrawlerInfo.setBookId(book.getId());
            List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getListByRecord(bookCrawlerInfo, bookCrawlerPage);

            List<Integer> allStatus = new LinkedList<>();
            for (BookCrawlerInfo crawlerInfo : bookCrawlerInfos) {
                if (StringUtils.isNotEmpty(crawlerInfo.getUrl())) {
                    allStatus.add(crawlerInfo.getStatus());
                }
            }

            if (allStatus.contains(CrawlerTaskStatusEnums.waitting.getStatus())) {
                bookStatus = IpBookStatusEnums.waitting.getStatus();
            } else if (allStatus.contains(CrawlerTaskStatusEnums.finished.getStatus())) {
                bookStatus = IpBookStatusEnums.finished.getStatus();
            }

            if (!bookStatus.equals(book.getStatus())) {
                book.setStatus(bookStatus);
                ipBookService.updateByRecord(book);
            }
        }

    }
}
