package com.dreamy.test.book;

import com.dreamy.admin.IndexCalculation.book.chuban.ChubanManage;
import com.dreamy.admin.handler.CrawlerFinishQueueHandler;
import com.dreamy.admin.handler.CrawlerNetbookFinishQueueHandler;
import com.dreamy.admin.tasks.rank.chuban.FlushBookRankToDb;
import com.dreamy.admin.tasks.rank.chuban.UpdateChubanBookIndexTask;
import com.dreamy.admin.tasks.rank.net.UpdateNetBookIndexTask;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.ipcool.RankService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/21
 * Time: 上午11:16
 */
public class BookViewTest extends BaseJunitTest {

    @Resource
    private BookInfoService bookInfoService;
    @Resource
    BookViewService bookViewService;

    @Autowired
    private BookScoreService bookScoreService;

    @Autowired
    private UpdateChubanBookIndexTask updateChubanBookIndexTask;

    @Autowired
    private QueueService queueService;

    @Autowired
    private FlushBookRankToDb flushBookRankToDb;

    @Autowired
    private CrawlerFinishQueueHandler crawlerFinishQueueHandler;

    @Autowired
    private CrawlerNetbookFinishQueueHandler crawlerNetbookFinishQueueHandler;

    @Autowired
    private UpdateNetBookIndexTask updateNetBookIndexTask;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private ChubanManage chubanManage;

    @Autowired
    private RankService rankService;

    @Value("${queue_crawler_over}")
    private String BookOverQueue;

    @Value("${queue_index_360}")
    private String s360IndexQueue;

    @Value("${queue_keyword_baidu}")
    private String baiduKeyWordQueue;

    @Value("${queue_keyword_so}")
    private String soKeyWordQueue;

    @Value("${queue_keyword_wb}")
    private String wbKeyWordQueue;

    @Value("${queue_keyword_wx}")
    private String wxKeyWordQueue;

    @Autowired
    private IpBookService ipBookService;

    @Autowired
    private NetBookInfoService netBookInfoService;


    /**
     * 清理文学的名称包含的特殊字符 和 作者中包含的特殊字符
     */
    @Test
    public void filterBookAuthorName() {
        int currentPage = 5;
        Page page = new Page();
        page.setPageSize(10000);

        page.setCurrentPage(currentPage);
        List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.net.getType());
        if (CollectionUtils.isNotEmpty(bookViewList)) {
            for (BookView bookView : bookViewList) {
                String bookName = bookView.getName();
                String author = bookView.getAuthor();

                Boolean isupdate = false;
                if (bookName.contains("(")) {
                    String[] temp = bookName.split("\\(");
                    bookName = temp[0];
                    isupdate = true;
                }

                if (author.contains("_")) {
                    String[] temp = author.split("_");
                    author = temp[0];
                    isupdate = true;
                }

                if (author.contains("(")) {
                    String[] temp = author.split("\\(");
                    author = temp[0];
                    isupdate = true;
                }

                if (author.contains("..")) {
                    String[] temp = author.split("\\..");
                    author = temp[0];
                    isupdate = true;
                }

                if (isupdate) {
                    bookView.name(bookName).author(author);
                    bookViewService.update(bookView);
                }
            }
        }
    }

    @Test
    public void netBookAuthorFixed() {
        int currentPage = 5;
        Page page = new Page();
        page.setPageSize(10000);

        page.setCurrentPage(currentPage);
        List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id asc", IpTypeEnums.net.getType());
        if (CollectionUtils.isNotEmpty(bookViewList)) {
            for (BookView bookView : bookViewList) {
                NetBookInfo netBookInfo = netBookInfoService.getById(bookView.getBookId());
                bookView.author(netBookInfo.getAuthor());
                bookViewService.update(bookView);
            }
        }
    }


}
