package com.dreamy.crawler;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.handler.keyword.KeyWordHandler;
import com.dreamy.handler.keyword.KeyWordWeiBoHandler;
import com.dreamy.handler.keyword.KeyWordWeiXinHandler;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.NumberUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * Created by wangyongxing on 16/4/29.
 */
public class KeyWordTest extends BaseJunitTest {

    @Autowired
    KeyWordWeiBoHandler keyWordWeiBoHandler;
    @Autowired
    KeyWordWeiXinHandler keyWordWeiXinHandler;
    @Autowired
    BookViewService bookViewService;

    @Test
    public void crawler() throws InterruptedException {

        keyWordWeiBoHandler.init();
//        BookView bookView = new BookView().type(2);
//        int currentPage = 8;
//        while (true) {
//            Page page = new Page();
//
//            page.setPageSize(50);
//            page.setCurrentPage(currentPage);
//            List<BookView> list = bookViewService.getList(bookView, page);
//
//            for (BookView book : list) {
//                keyWordWeiXinHandler.crawler(book.getName(), book.getBookId());
//                Thread.sleep(NumberUtils.randomInt(1000,5000));
//            }
//            System.out.println(111);
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
    @Test
    public  void test(){
        keyWordWeiBoHandler.crawler("围城",111);
    }



}
