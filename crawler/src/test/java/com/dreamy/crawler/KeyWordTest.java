package com.dreamy.crawler;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.QueueRoutingKeyEnums;
import com.dreamy.handler.keyword.KeyWordHandler;
import com.dreamy.handler.keyword.KeyWordWeiXinHandler;
import com.dreamy.handler.keyword.sina.CrawSina;
import com.dreamy.handler.keyword.sina.SinaHttpUtils;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.IpBookService;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by wangyongxing on 16/4/29.
 */
public class KeyWordTest extends BaseJunitTest {

    @Autowired
    KeyWordHandler keyWordHandler;
    @Autowired
    KeyWordWeiXinHandler keyWordWeiXinHandler;
    @Autowired
    BookViewService bookViewService;

    @Test
    public void crawler() throws InterruptedException {
        keyWordHandler.init();
//        BookView bookView = new BookView().type(1);
//        int currentPage =  1;
//        while (true) {
//            Page page = new Page();
//            page.setPageSize(200);
//            page.setCurrentPage(currentPage);
//            List<BookView> list = bookViewService.getList(bookView, page);
//
//            for (BookView book : list) {
//                keyWordWeiXinHandler.crawler(book.getName() + " " + book.getAuthor(), book.getBookId());
//                Thread.sleep(getRandom(50000));
//
//            }
//        }
//        IpBook ipBook = new IpBook().type(1);
//        int currentPage =  1;
//        while (true) {
//            Page page = new Page();
//            page.setPageSize(20);
//            page.setCurrentPage(currentPage);
//            List<IpBook> list = ipBookService.getIpBookList(ipBook, page);
//            System.out.println(page.getTotalPage());
//            if (!page.isHasNextPage()) {
//                break;
//            }
//            currentPage++;
//        }



    }

    private static int getRandom(int size){
        Random random=new Random();
        int result=random.nextInt(size);
        return result;
    }



}
