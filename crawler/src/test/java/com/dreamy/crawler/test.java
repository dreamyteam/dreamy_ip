package com.dreamy.crawler;


import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.handler.*;
import com.dreamy.handler.so.SoHandler;
import com.dreamy.mogodb.beans.*;
import com.dreamy.mogodb.dao.*;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.TimeUtils;
import com.mongodb.DBObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by wangyongxing on 16/4/5.
 */
public class test extends BaseJunitTest {

    private Date lastRuntime = null;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserAgentDao userAgentDao;

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private SoHandler soHandler;
    @Autowired
    BookIndexDataDao bookIndexDataDao;

    @Autowired
    BookInfoDao bookInfoDao;
    @Autowired
    CommentHandler commentHandler;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    private CommentDao commentDao;

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
        bookInfo.setTitle("c" + "");

    }

    @Test
    public void testDouban() {
        String url = "https://book.douban.com/subject/1082334/";
        CrawlerHandler crawlerHandler = new DouBanCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
    }

    @Test
    public void testDangdang() {
        String url = "http://product.dangdang.com/23353342.html";
        CrawlerHandler crawlerHandler = new DangDangCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
        assertNotNull(bookInfo);
    }

    @Test
    public void testAm() {
        String url = "https://www.amazon.cn/%E4%B8%9C%E9%87%8E%E5%9C%AD%E5%90%BE-%E8%A7%A3%E5%BF%A7%E6%9D%82%E8%B4%A7%E5%BA%97-%E4%B8%9C%E9%87%8E%E5%9C%AD%E5%90%BE/dp/B00JZ96ZI8/ref=sr_1_1?ie=UTF8&qid=1460946961&sr=8-1&keywords=%E8%A7%A3%E5%BF%A7%E6%9D%82%E8%B4%A7%E5%BA%97";
        url = "https://www.amazon.cn/dp/B00MGKFQ6U/ref=tmm_hrd_swatch_0?_encoding=UTF8&qid=1461228641&sr=1-1";
        CrawlerHandler crawlerHandler = new AmazonCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }
        assertNotNull(bookInfo);
    }

    @Test
    public void testJd() {
        String url = "http://item.jd.com/11425143.html";
        CrawlerHandler crawlerHandler = new JdCrawlerHandler();
        BookInfo bookInfo = (BookInfo) crawlerHandler.getByUrl(url);
        if (bookInfo != null) {

        }

        assertNotNull(bookInfo);
    }

    @Test
    public void testGetMongo() {
        Object object = bookInfoService.getById(1);
        if (object != null) {

        }
    }

    @Test
    public void find() {
        Criteria criteria = Criteria.where("source").is(4);
        Query query = new Query(criteria);
//        BookInfo bookInfo= bookInfoDao.queryOne(query);
//        System.out.println(111);
    }

    @Test
    public void userAgents() {
//        String html = SinaHttpUtils.getHtmlGet("http://ua.theafh.net/");
//        Document document = Jsoup.parse(html);
//        Element element = document.getElementById("result");
//
//        Integer i=1;
//        UserAgents userAgents1 = new UserAgents();
//        for (Node node : element.childNodes().get(0).childNodes()) {
//            String tmp = node.childNodes().get(1).childNode(0).toString();
//            userAgents1.setId(i);
//            userAgents1.setUserAgent(tmp);
//            userAgentDao.save(userAgents1);
//            i++;
//        }
        UserAgents userAgents1 = userAgentDao.queryById(933);
        BookInfo bookInfo = bookInfoService.getById(398);
//        BookInfo old = bookInfoService.getById(410);
//        if (old != null) {
//            bookInfoService.delById(410);
//        }
//
//        Random random = new Random();
//        long t1 = System.nanoTime();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(random.nextInt(1000));
//        }
//        long   t2 = System.nanoTime();
        System.out.println(11);

    }

    @Test
    public void tt() {
//        Date currentTime = new Date();
//        if (lastRuntime == null) {
//            lastRuntime = currentTime;
//        }
//        try {
//            Thread.currentThread().sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        currentTime = new Date();
//        long time = TimeUtils.diff(lastRuntime, currentTime);

        long aa = 1000;
        Random random = new Random();
        Integer timeRange = random.nextInt(3000);
        long bb = aa + (long) timeRange;
        System.out.print(bb);
    }

    @Test
    public void so() throws UnsupportedEncodingException {
        BookIndexData bookIndexData = soHandler.getByUrl("少有人走的路", "全国");
        bookIndexData.setId(110);
        bookIndexData.setSource(2);
        bookIndexDataDao.save(bookIndexData);

    }


    @Test
    public void bookIndexDataFind() throws UnsupportedEncodingException {

        BookIndexData bookIndexData = bookIndexDataDao.queryById(110);

        System.out.println(bookIndexData);

    }

    @Test
    public void find1() {
        DBObject dbObject = bookInfoDao.getList();
        System.out.println(11);
    }

    @Test
    public void comment() {
        BookCrawlerInfo crawlerInfo=new BookCrawlerInfo().source(4);
        List<BookCrawlerInfo> list1= bookCrawlerInfoService.getByRecord(crawlerInfo);
        for(BookCrawlerInfo info:list1){
            String url = info.getUrl();
            if(StringUtils.isNotEmpty(url)) {
                List<Comment> commentList = commentHandler.getByUrl(url);
                if(CollectionUtils.isNotEmpty(commentList))
                {
                    Comments comment=new Comments();
                    comment.setIpId(info.getBookId());
                    comment.setComments(commentList);
                    commentDao.save(comment);
                }
            }
        }



    }

}
