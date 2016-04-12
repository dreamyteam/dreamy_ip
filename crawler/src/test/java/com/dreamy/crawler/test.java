package com.dreamy.crawler;

import com.dreamy.handler.AmazonCrawlerHandler;
import com.dreamy.handler.CrawlerHandler;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.Member;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.mogodb.dao.MemberDao;
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


    public void testLetvAlbum() {
        String url="http://www.amazon.cn/gp/product/B00VWVAFAG/ref=s9_acsd_ri_bw_rw_r0_p8_i?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=merchandised-search-5&pf_rd_r=0KFAPX9E42KMYPW0V164&pf_rd_t=101&pf_rd_p=261616452&pf_rd_i=658390051";
        CrawlerHandler crawlerHandler = new AmazonCrawlerHandler();
        BookInfo albumBean = (BookInfo) crawlerHandler.getByUrl(url);
        if (albumBean != null) {

        }
        assertNotNull(albumBean);
    }
}
