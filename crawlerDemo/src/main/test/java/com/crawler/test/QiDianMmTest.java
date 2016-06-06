package com.crawler.test;

import com.dreamy.crawler.handler.info.netbook.qd.QiDian;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.dao.NetBookInfoDao;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.utils.PatternUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/31.
 */
public class QiDianMmTest extends BaseJunitTest {
    @Autowired
    IpBookService ipBookService;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    NetBookInfoDao netBookInfoDao;
    @Test
    public void save(){
        for(int i=192;i<217;i++) {
            String url="http://all.qdmm.com/MMWeb/BookStore.aspx?ChannelId=41&SubCategoryId=-1&Tag=all&Size=-1&Action=-1&OrderId=6&P=all&PageIndex="+i+"&update=-1&Vip=1";
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(100), QiDian.class);
            QiDian qiDian = ooSpider.<QiDian>get(url);
            NetBookInfo info =null;
            int size = qiDian.getUrls().size();
            List<String> urls = qiDian.getUrls();
            List<String> names = qiDian.getTitles();
            List<String> authoers = qiDian.getAuthoers();
            List<String> authoerUrls = qiDian.getAuthoerUrls();
            for (int j = 0; j< size; j++) {
                 info = new NetBookInfo();
                String utl = urls.get(j);
                info.setTitle(names.get(j));
                info.setAuthor(authoers.get(j));
                info.setAuthor(authoers.get(j));
                info.setAuthorUrl(authoerUrls.get(j));
                String code = PatternUtils.getNum(utl);
                info.setImage("http://image.cmfu.com/books/" + code + "/" + code + ".jpg");
                try {
                    IpBook ipBook = new IpBook();
                    ipBook.setType(2);
                    ipBook.setStatus(1);
                    ipBook.setTitle(names.get(j));
                    ipBook.name(names.get(j));
                    ipBook.setCode(code);
                    ipBookService.save(ipBook);
                    info.setBookId(ipBook.getId());
                    netBookInfoDao.save(info);
                    BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                    bookCrawlerInfo.status(1);
                    bookCrawlerInfo.bookId(ipBook.getId());
                    bookCrawlerInfo.setSource(CrawlerSourceEnums.qidianmm.getType());
                    bookCrawlerInfo.url(utl);
                    bookCrawlerInfoService.save(bookCrawlerInfo);
                }
                catch (Exception e){
                    System.out.println(code);
//                    e.printStackTrace();
                    continue;
                }

            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
