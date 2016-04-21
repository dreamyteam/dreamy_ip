package com.dreamy.crawler;

import com.dreamy.handler.*;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.Member;
import com.dreamy.mogodb.dao.MemberDao;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/5.
 */
public class test extends BaseJunitTest {
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private BookInfoService bookInfoService;

    @Test
    public void insert() {
        System.out.println(11);

        Book book = new Book();
        book.setTitle(" 测试");
        book.setCode("0001");
        List<Member> list = new ArrayList<Member>();
        Member member = new Member();
        member.setAuthor("test");
        member.setImage("test");
        member.setTitle("test");
        list.add(member);

        Member member2 = new Member();
        member2.setAuthor("test");
        member2.setImage("test");
        member2.setTitle("test");
        list.add(member2);
        book.setMemberList(list);

        memberDao.save(book);
    }

    public void insertBookInfo() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle("c" +
                "");

    }

    @Test
    public void testDouban() {
        String url = "https://book.douban.com/subject/1770782/";
        CrawlerHandler crawlerHandler = new DouBanCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
    }

    @Test
    public void testDangdang() {
        String url = "http://product.dangdang.com/23274638.html?ref=book-65152-9162_1-473554-0";
        CrawlerHandler crawlerHandler = new DangDangCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
        assertNotNull(bookInfo);
    }

    @Test
    public void testAm() {
        String url = "https://www.amazon.cn/%E4%B8%9C%E9%87%8E%E5%9C%AD%E5%90%BE-%E8%A7%A3%E5%BF%A7%E6%9D%82%E8%B4%A7%E5%BA%97-%E4%B8%9C%E9%87%8E%E5%9C%AD%E5%90%BE/dp/B00JZ96ZI8/ref=sr_1_1?ie=UTF8&qid=1460946961&sr=8-1&keywords=%E8%A7%A3%E5%BF%A7%E6%9D%82%E8%B4%A7%E5%BA%97";
        CrawlerHandler crawlerHandler = new AmazonCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
        assertNotNull(bookInfo);
    }

    @Test
    public void testJd() {
        String url = "http://item.jd.com/11452840.html";
        CrawlerHandler crawlerHandler = new JdCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
        assertNotNull(bookInfo);
    }

    @Test
    public void testGetMongo() {
        Object object = bookInfoService.queryById(218);
        if (object != null) {

        }
    }
}
