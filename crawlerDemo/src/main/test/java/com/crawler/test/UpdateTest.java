package com.crawler.test;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import com.dreamy.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangyongxing
 * Date: 16/6/21
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */
public class UpdateTest extends BaseJunitTest {

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Autowired
    private NetBookInfoService netBookInfoService;
    @Autowired
    BookViewService bookViewService;

    @Test
    public void test() {
        BookCrawlerInfo entity = new BookCrawlerInfo().source(CrawlerSourceEnums.huayu.getType());
        Page page = new Page();
        page.setPageSize(200);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<BookCrawlerInfo> list = bookCrawlerInfoService.getListByRecord(entity, page);
            for (BookCrawlerInfo info : list) {
                NetBookInfo aa = netBookInfoService.getById(info.getBookId());
                if (aa != null) {
                    BookView old = bookViewService.getByBookId(info.getBookId());
                    if (old != null) {
                        BookView bookView = new BookView();
                        bookView.setId(old.getId());
                        bookView.setAuthor(aa.getAuthor());
                        bookViewService.update(bookView);

                    }
                }

            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }
}
