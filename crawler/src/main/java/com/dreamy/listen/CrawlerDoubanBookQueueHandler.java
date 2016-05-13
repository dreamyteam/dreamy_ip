package com.dreamy.listen;

import com.alibaba.fastjson.JSONObject;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.handler.book.DouBanBookCrawlerHandler;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.CrawlerService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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


    @Override
    public void consume(JSONObject jsonObject) {

        String title = jsonObject.getString("title");
        String url = jsonObject.getString("url");
        try {
            BookInfo bookInfo = douBanBookCrawlerHandler.crawler(url);
            bookInfo.setSource(CrawlerSourceEnums.douban.getType());
            bookInfoService.updateInser(bookInfo);
            IpBook ipBook=new IpBook();
            ipBook.setType(1);
            ipBook.setStatus(1);
            ipBook.setTitle(title);
            ipBook.name(title);
            ipBook.setCode(bookInfo.getISBN());
            ipBookService.save(ipBook);
            if(StringUtils.isNotEmpty(bookInfo.getISBN())) {
                crawlerService.push(bookInfo.getISBN());
            }
            else{
                System.out.println(bookInfo.getTitle());
            }
            Thread.sleep(1000+ NumberUtils.randomInt(1000,2000));
        } catch (Exception e) {

            log.error("crawler event exception" + title + ",url:" + url, e);

        }
    }
}
