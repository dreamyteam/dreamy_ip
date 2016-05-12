package com.dreamy.crawler;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.handler.so.SoHandler;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.dao.BookIndexDataDao;
import com.dreamy.mogodb.dao.BookInfoDao;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public class SoIndexTest extends BaseJunitTest {
    @Autowired
    private SoHandler soHandler;
    @Autowired
    BookIndexDataDao bookIndexDataDao;
    @Autowired
    IpBookService ipBookService;


    @Test
    public void so() throws UnsupportedEncodingException, InterruptedException {

        Page page = new Page();
        page.setPageSize(200);
        IpBook ipBook = new IpBook().type(1);
        List<IpBook> list = ipBookService.getIpBookList(ipBook, page);
        for (IpBook book : list) {
            BookIndexData bookIndexData = soHandler.getByUrl(book.getName(), "全国");
            bookIndexData.setId(book.getId());
            bookIndexData.setSource(2);
            bookIndexDataDao.updateInser(bookIndexData);
            Thread.sleep(1000);
        }


    }

    @Test
    public void soOne() throws UnsupportedEncodingException, InterruptedException {

        IpBook book = ipBookService.getById(52);
        BookIndexData bookIndexData = soHandler.getByUrl(book.getName(), "全国");
        bookIndexData.setId(book.getId());
        bookIndexData.setSource(2);
        bookIndexDataDao.updateInser(bookIndexData);
        //Thread.sleep(1000);


    }
}
