package com.crawler.test;

import com.dreamy.crawler.handler.info.netbook.ZongHeng;
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
public class ZonghengTest extends BaseJunitTest {
    @Autowired
    IpBookService ipBookService;
    @Autowired
    BookCrawlerInfoService bookCrawlerInfoService;
    @Autowired
    NetBookInfoDao netBookInfoDao;
    @Test
    public void save(){
        for(int i=1;i<100;i++) {
            String url="http://book.zongheng.com/store/c0/c0/b1/u0/p"+i+"/v1/s9/t0/ALL.html";
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(100), ZongHeng.class);
            ZongHeng zongHeng = ooSpider.<ZongHeng>get(url);
            NetBookInfo info =null;
            int size = zongHeng.getUrls().size();
            List<String> urls = zongHeng.getUrls();
            List<String> names = zongHeng.getTitles();
            List<String> authoers = zongHeng.getAuthoers();
            for (int j = 0; j< size; j++) {
                 info = new NetBookInfo();
                String utl = urls.get(j);
                info.setTitle(names.get(j));
                info.setAuthor(authoers.get(j));
                String code = PatternUtils.getNum(utl);
                IpBook ipBook = new IpBook();
                ipBook.setType(2);
                ipBook.setStatus(1);
                ipBook.setTitle(names.get(j));
                ipBook.name(names.get(j));
                ipBook.setCode(code+"_"+CrawlerSourceEnums.huayu.getType());
                ipBookService.save(ipBook);
                info.setBookId(ipBook.getId());
                netBookInfoDao.save(info);
                BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo();
                bookCrawlerInfo.status(1);
                bookCrawlerInfo.bookId(ipBook.getId());
                bookCrawlerInfo.setSource(CrawlerSourceEnums.huayu.getType());
                bookCrawlerInfo.url(utl);
                bookCrawlerInfoService.save(bookCrawlerInfo);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
