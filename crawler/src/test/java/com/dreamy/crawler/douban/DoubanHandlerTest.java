package com.dreamy.crawler.douban;

import com.dreamy.crawler.BaseJunitTest;
import com.dreamy.handler.book.DouBanBookCrawlerHandler;
import com.dreamy.handler.book.DouBanBookHandler;
import com.dreamy.mogodb.beans.BookInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyongxing on 16/5/13.
 */
public class DoubanHandlerTest extends BaseJunitTest {

    @Autowired
    DouBanBookHandler douBanBookHandler;
    @Autowired
    DouBanBookCrawlerHandler douBanBookCrawlerHandler;
    @Test
    public void  crawler() throws Exception {
        douBanBookHandler.crawler("传记");
    }
    @Test
    public void crawlISBN(){
        BookInfo info= douBanBookCrawlerHandler.crawler("https://book.douban.com/subject/1830445/");
        System.out.println(info.getAuthor());
        System.out.println(111);
    }
}
