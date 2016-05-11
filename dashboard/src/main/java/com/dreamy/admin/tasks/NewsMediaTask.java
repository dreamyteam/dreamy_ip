package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.QueueRoutingKeyEnums;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.mq.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/11.
 * news.sogou.com上5大新闻来源的新闻数量
 */
@Component
public class NewsMediaTask {

    @Resource
    private QueueService queueService;
    @Resource
    BookViewService bookViewService;

    public void crawler() {

        BookView bookView = new BookView().type(1);
        int currentPage = 1;
        while (true) {
            Page page = new Page();
            page.setPageSize(50);
            page.setCurrentPage(currentPage);
            List<BookView> list = bookViewService.getList(bookView, page);
            for (BookView book : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("source", book.getType());
                map.put("bookId", book.getBookId());
                map.put("word", book.getName());
                queueService.push(QueueRoutingKeyEnums.publish_news_sougou.getKey(), map);

            }
            if (!page.isHasNextPage()) {
                break;
            }
            currentPage++;
        }
    }

}
