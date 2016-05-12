package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.enums.IpBookStatusEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/21
 * Time: 下午4:58
 */
@Component
public class IpBookCrawlerTask {
    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Resource
    private IpBookService ipBookService;

    @Autowired
    private BookInfoService bookInfoService;

    @Scheduled(fixedDelay = 5000)
    public void checkBookCrawlerWaittingStatus() {
        int currentPage = 1;
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().status(CrawlerTaskStatusEnums.waitting.getStatus());
        try {
            while (true) {
                Page page = new Page();
                page.setPageSize(50);
                page.setCurrentPage(currentPage);
                List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getListByRecord(bookCrawlerInfo, page);
                if (CollectionUtils.isNotEmpty(bookCrawlerInfos)) {
                    for (BookCrawlerInfo info : bookCrawlerInfos) {
                        ipBookService.doCrawler(info);
                    }
                }
                if (!page.isHasNextPage()) {
                    break;
                }
                currentPage++;
                Thread.sleep(NumberUtils.randomInt(10, 30) * 1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 7000)
    public void checkIpBookStatus() {
        Page page = new Page();
        page.setPageSize(1000);

        IpBook ipBook = new IpBook().status(IpBookStatusEnums.waitting.getStatus());
        List<IpBook> ipBooks = ipBookService.getIpBookList(ipBook, page);
        if (CollectionUtils.isEmpty(ipBooks)) {
            ipBook.setStatus(IpBookStatusEnums.starting.getStatus());
            ipBooks = ipBookService.getIpBookList(ipBook, page);
            if (CollectionUtils.isEmpty(ipBooks)) {
                ipBook.setStatus(IpBookStatusEnums.exception.getStatus());
                ipBooks = ipBookService.getIpBookList(ipBook, page);
                if (CollectionUtils.isEmpty(ipBooks)) {
                    return;
                }
            }
        }

        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
        Page bookCrawlerPage = new Page();
        for (IpBook book : ipBooks) {
            Integer bookStatus = book.getStatus();

            bookCrawlerInfo.setBookId(book.getId());
            List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getListByRecord(bookCrawlerInfo, bookCrawlerPage);

            Set<Integer> allStatus = new HashSet<>();
            for (BookCrawlerInfo crawlerInfo : bookCrawlerInfos) {
                if (StringUtils.isNotEmpty(crawlerInfo.getUrl())) {
                    allStatus.add(crawlerInfo.getStatus());
                }
            }

            if (allStatus.contains(CrawlerTaskStatusEnums.failed.getStatus())) {
                bookStatus = IpBookStatusEnums.exception.getStatus();
            } else if (allStatus.contains(CrawlerTaskStatusEnums.starting.getStatus())) {
                bookStatus = IpBookStatusEnums.starting.getStatus();
            } else if (allStatus.contains(CrawlerTaskStatusEnums.success.getStatus())) {
                bookStatus = IpBookStatusEnums.finished.getStatus();
            }

            if (!bookStatus.equals(book.getStatus())) {
                book.setStatus(bookStatus);
                ipBookService.updateByRecord(book);
            }
        }

    }



    public void crawler() {
        int currentPage = 1;
        BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
        try {
            while (true) {
                Page page = new Page();
                page.setPageSize(20);
                page.setCurrentPage(currentPage);
                List<BookCrawlerInfo> bookCrawlerInfos = bookCrawlerInfoService.getListByRecord(bookCrawlerInfo, page);
                if (CollectionUtils.isNotEmpty(bookCrawlerInfos)) {
                    for (BookCrawlerInfo info : bookCrawlerInfos) {
                        ipBookService.doCrawler(info);
                    }
                }
                if (!page.isHasNextPage()) {
                    break;
                }
                currentPage++;
                Thread.sleep(NumberUtils.randomInt(10, 20) * 1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
