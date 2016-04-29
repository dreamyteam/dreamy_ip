package com.dreamy.crawler;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.handler.keyword.KeyWordHandler;
import com.dreamy.service.iface.ipcool.IpBookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/29.
 */
public class KeyWordTest extends BaseJunitTest {

    @Autowired
    KeyWordHandler keyWordHandler;
    @Autowired
    IpBookService ipBookService;

    @Test
    public void crawler() {

        Page page = new Page();
        page.setPageSize(200);
        IpBook ipBook = new IpBook().type(1);
        List<IpBook> list = ipBookService.getIpBookList(ipBook, page);
        for (IpBook book : list) {
            keyWordHandler.crawler(book.getName(), book.getId());

        }


    }

}
