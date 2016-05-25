package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.handler.book.DouBanBookCrawlerHandler;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.CrawlerService;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class CrawlerDoubanBookQueueHandler extends AbstractQueueHandler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerDoubanBookQueueHandler.class);


    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private DouBanBookCrawlerHandler douBanBookCrawlerHandler;
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private IpBookService ipBookService;

    @Resource
    private BookCrawlerInfoService bookCrawlerInfoService;


    @Override
    public void consume(JSONObject jsonObject) {

        String title = jsonObject.getString("title");
        String url = jsonObject.getString("url");
        try {
            BookInfo bookInfo = douBanBookCrawlerHandler.crawler(url);
            if (bookInfo != null && StringUtils.isNotEmpty(bookInfo.getTitle())) {
                IpBook ipBook = new IpBook();
                ipBook.setType(1);
                ipBook.setStatus(1);
                ipBook.setTitle(title);
                ipBook.name(title);
                ipBook.setCode(bookInfo.getISBN());
                ipBookService.save(ipBook);
                bookInfo.setSource(CrawlerSourceEnums.douban.getType());
                bookInfo.setIpId(ipBook.getId());
                bookInfo.setId(bookInfo.getISBN() + "_" + CrawlerSourceEnums.douban.getType());
                bookInfoService.updateInser(bookInfo);
                BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                bookCrawlerInfo.status(1);
                bookCrawlerInfo.bookId(ipBook.getId());
                bookCrawlerInfo.setSource(CrawlerSourceEnums.douban.getType());
                bookCrawlerInfo.url(url);
                bookCrawlerInfoService.save(bookCrawlerInfo);
                if (StringUtils.isNotEmpty(bookInfo.getISBN())) {
                    crawlerService.pushAll(bookInfo.getISBN(), url, ipBook.getId());
                }
            }
            Thread.sleep(NumberUtils.randomInt(1000, 3000));
        } catch (Exception e) {

            log.error("CrawlerDoubanBookQueueHandler event exception" + title + ",url:" + url, e);

        }
    }
}
