package com.dreamy.crawler;

import com.dreamy.handler.CrawlerHandler;
import com.dreamy.handler.DangDangCrawlerHandler;
import com.dreamy.handler.DouBanCrawlerHandler;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.Member;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.MemberDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/5.
 */
public class test extends BaseJunitTest {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private BookInfoDao bookInfoDao;

    @Test
    public void insert()
    {
        System.out.println(11);

        Book book=new Book();
        book.setTitle(" 测试");
        book.setCode("0001");
        List<Member> list=new ArrayList<Member>();
        Member member=new Member();
        member.setAuthor("test");
        member.setImage("test");
        member.setTitle("test");
        list.add(member);

        Member member2=new Member();
        member2.setAuthor("test");
        member2.setImage("test");
        member2.setTitle("test");
        list.add(member2);
        book.setMemberList(list);

        memberDao.save(book);
    }

    public  void insertBookInfo()
    {
        BookInfo bookInfo=new BookInfo();
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
    public void find(){
        Criteria criteria = Criteria.where("source").is(4);
        Query query=new Query(criteria);
        BookInfo bookInfo= bookInfoDao.queryOne(query);
        System.out.println(111);
    }
}
