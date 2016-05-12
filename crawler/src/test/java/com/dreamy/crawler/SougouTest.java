package com.dreamy.crawler;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.handler.sougou.NewsSougouHandler;
import com.dreamy.service.iface.ipcool.BookViewService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/6.
 */
public class SougouTest extends BaseJunitTest {


    @Autowired
    NewsSougouHandler newsSougouHandler;
    @Autowired
    BookViewService bookViewService;

    @Test
    public void crawler() throws InterruptedException {

        BookView bookView = new BookView().type(1);
        int currentPage = 1;
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(currentPage);
            List<BookView> list = bookViewService.getList(bookView, page);

            for (BookView book : list) {
                newsSougouHandler.crawler(book.getName(), book.getBookId());
            }
            if (!page.isHasNextPage()) {
                break;
            }
            currentPage++;
        }
    }

}
