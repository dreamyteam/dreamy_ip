package com.dreamy.crawler.douban;

import com.dreamy.beans.Page;
import com.dreamy.crawler.BaseJunitTest;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.service.CrawlerService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class PushTest extends BaseJunitTest {
    @Autowired
    CrawlerService crawlerService;
    @Autowired
    private IpBookService ipBookService;

    @Test
    public void push() throws InterruptedException {

        IpBook entity = new IpBook();
        int currentPage = 1;
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(currentPage);
            List<IpBook> list = ipBookService.getIpBookList(entity, page);

            for (IpBook book : list) {
                if (StringUtils.isNotEmpty(book.getCode())) {
                    crawlerService.pushAll(book.getCode(),"",0);
                }

            }
            if(!page.isHasNextPage()){
                break;
            }
            currentPage++;
        }
    }
}
