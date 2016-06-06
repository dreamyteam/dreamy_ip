package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/3.
 * 关键词搜索
 */
@Component
public class KeyWorkTask {

    private static final Logger log = LoggerFactory.getLogger(KeyWorkTask.class);


    @Autowired
    BookViewService bookViewService;

    @Resource
    private QueueService queueService;

    @Value("${queue_keyword_baidu_sougou}")
    private String queueName;


    @Value("${queue_keyword_wx}")
    private String weiXinQueueName;

    @Value("${queue_keyword_wb}")
    private String weiBoQueueName;


    public void crawler() {

        BookView bookView = new BookView().type(1);
        int currentPage = 1;
        try {
            while (true) {
                Page page = new Page();
                page.setPageSize(20);
                page.setCurrentPage(currentPage);
                List<BookView> list = bookViewService.getList(bookView, page,null);
                for (BookView book : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("source", book.getType());
                    map.put("bookId", book.getBookId());
                    if (StringUtils.isNotEmpty(book.getAuthor())) {
                        map.put("word", book.getName() + " " + book.getAuthor());
                    } else {
                        map.put("word", book.getName());
                    }
                    queueService.push(queueName, map);

                }
                if (!page.isHasNextPage()) {
                    break;
                }
                currentPage++;
            }
        } catch (Exception e) {

            log.error("crawler is error", e);
        }

    }

    public void crawlerWeiXin() {
        try {
            BookView entity = new BookView().type(2);
            int currentPage = 1;
            while (true) {
                Page page = new Page();
                page.setPageSize(50);
                page.setCurrentPage(currentPage);
                List<BookView> list = bookViewService.getList(entity, page,null);
                for (BookView bookView : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("bookId", bookView.getBookId());
                    map.put("source", bookView.getType());
                    map.put("word", bookView.getName());
                    queueService.push(weiXinQueueName, map);
                }
                if (!page.isHasNextPage()) {
                    break;
                }
                currentPage++;
            }
        } catch (Exception e) {
            log.error("crawlerWeiXin is error", e);
        }
    }

    public void crawlerWeiBo() {
        try {
            BookView entity = new BookView().type(1);
            int currentPage = 1;
            while (true) {
                Page page = new Page();
                page.setPageSize(50);
                page.setCurrentPage(currentPage);
                List<BookView> list = bookViewService.getList(entity, page,null);
                for (BookView bookView : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("source", bookView.getType());
                    map.put("bookId", bookView.getBookId());
                    if (StringUtils.isNotEmpty(bookView.getAuthor())) {
                        map.put("word", bookView.getName() + " " + bookView.getAuthor());
                    } else {
                        map.put("word", bookView.getName());
                    }
                    queueService.push(weiBoQueueName, map);
                }
                if (!page.isHasNextPage()) {
                    break;
                }
                currentPage++;
            }
        } catch (Exception e) {
            log.error("crawlerWeiBo is error", e);
        }
    }


//    public void crawlerWeiXin(final List<BookView> list) {
//        AsynchronousService.submit(new ObjectCallable() {
//            @Override
//            public Object run() throws Exception {
//                for (BookView book : list) {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("bookId", book.getBookId());
//                    map.put("source", book.getType());
//                    if (StringUtils.isNotEmpty(book.getAuthor())) {
//                        map.put("word", book.getName() + " " + book.getAuthor());
//                    } else {
//                        map.put("word", book.getName());
//                    }
//                    queueService.push(weiXinQueueName, map);
//                }
//                return null;
//            }
//        });
//    }


}
