package com.dreamy.test.crawler;

import com.dreamy.admin.IndexCalculation.book.chuban.ChubanBookSourceBaseHandler;
import com.dreamy.admin.IndexCalculation.book.chuban.ChubanManage;
import com.dreamy.admin.handler.CrawlerFinishQueueHandler;
import com.dreamy.admin.handler.CrawlerNetbookFinishQueueHandler;
import com.dreamy.admin.tasks.rank.FlushBookRankToDb;
import com.dreamy.admin.tasks.rank.UpdateChubanBookIndexTask;
import com.dreamy.admin.tasks.rank.UpdateNetBookIndexTask;
import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.RankService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.HttpUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
public class IpBookTest extends BaseJunitTest {
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

    @Test
    public void insert() {
        List<BookInfo> list = bookInfoService.getListByIpId(151);
        BookView view = null;
        for (BookInfo bookInfo : list) {
            if (bookInfo.getSource() == 4) {
                view = new BookView();
                view.setName(bookInfo.getTitle());
                view.setAuthor(bookInfo.getAuthor());
                view.introduction(bookInfo.getInfo());
                view.bookId(bookInfo.getIpId());
                view.imageUrl(bookInfo.getImage());
                view.status(0);
                view.type(1);
                bookViewService.save(view);

                view.compositeIndex(9999);
                view.hotIndex(5555);
                view.activityIndex(3333);
                view.propagateIndex(666);
                view.developIndex(4444);
                bookViewService.update(view);

            }

        }

    }

    @Test
    public void update() {
        BookView bookView = new BookView();
        bookView.id(1);
        bookView.bookId(110);
        bookView.compositeIndex(10000);
        bookView.hotIndex(8888);
        bookView.activityIndex(6666);
        bookView.propagateIndex(3333);
        bookView.developIndex(7777);
        bookViewService.update(bookView);
    }

    @Test
    public void bookview() {
        Integer bookId = 40;
        List<BookInfo> bookInfos = bookInfoService.getListByIpId(bookId);
        if (CollectionUtils.isNotEmpty(bookInfos)) {
            BookView bookView = new BookView();
            Map<Integer, BookInfo> bookInfoMap = new HashMap<>();
            for (BookInfo bookInfo : bookInfos) {
                bookInfoMap.put(bookInfo.getSource(), bookInfo);
            }

            Map<Integer, BookInfo> newBookInfoMap = new HashMap<>();
//            if (bookInfos.contains(CrawlerSourceEnums.douban.getType())) {
//                newBookInfoMap.put(CrawlerSourceEnums.douban.getType(), bookInfos.get(CrawlerSourceEnums.douban.getType()));
//            }


        }
    }

    @Test
    public void updateInser() {
        BookInfo bookInfo = bookInfoService.getById("");
        bookInfo.setCrawlerId(20000);
        bookInfo.setCommentNum(21212);
        bookInfo.setAuthor("测试");
        bookInfo.setSaleSort(12);
        bookInfo.setInfo("adadads");
        bookInfoService.updateInser(bookInfo);
    }

    @Test
    public void developIndex() {

        int currentPage = 1;
        Page page = new Page();
        page.setPageSize(100);
        Boolean isLoop = true;

        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.net.getType());
                if (CollectionUtils.isNotEmpty(bookViewList)) {
                    for (BookView bookView : bookViewList) {
                        crawlerNetbookFinishQueueHandler.updateNet(bookView);
                    }
                    currentPage++;
                } else {
                    isLoop = false;
                }

            } catch (Exception e) {
                break;
            }
        }
    }

    @Test
    public void lnTest() {
        int currentPage = 5;
        Page page = new Page();
        page.setPageSize(10000);

        try {
            page.setCurrentPage(currentPage);
            List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id asc", IpTypeEnums.net.getType());
            if (CollectionUtils.isNotEmpty(bookViewList)) {
                for (BookView bookView : bookViewList) {
                    crawlerNetbookFinishQueueHandler.updateNet(bookView);
                }
            }

        } catch (Exception e) {
            System.err.println("errlr");
        }

//        BookView bookView = bookViewService.getById(1428);
//        crawlerNetbookFinishQueueHandler.updateNet(bookView);
    }


    @Test
    public void s360Index() {
        int currentPage = 1;
        Page page = new Page();
        page.setPageSize(100);
        Boolean isLoop = true;

        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", IpTypeEnums.chuban.getType());
                if (CollectionUtils.isNotEmpty(bookViewList)) {
                    for (BookView bookView : bookViewList) {

                        Map<String, String> commonParams = rankService.getCommonParamsByBookIdAndAction(bookView, OperationEnums.update.getCode());
                        String cacheKey = commonParams.get("key");
                        redisClientService.setNumber(cacheKey, 1L);
                        commonParams.put("type", IpTypeEnums.chuban.getType().toString());
                        commonParams.put("name", bookView.getName());
                        queueService.push(s360IndexQueue, commonParams);
                    }
                    currentPage++;
                } else {
                    isLoop = false;
                }

            } catch (Exception e) {
                break;
            }
        }
    }

    @Test
    public void flushRank() {
        int currentPage = 3;
        Page page = new Page();
        page.setPageSize(1000);

        try {
            page.setCurrentPage(currentPage);
            List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id asc", IpTypeEnums.chuban.getType());
            if (CollectionUtils.isNotEmpty(bookViewList)) {
                for (BookView bookView : bookViewList) {
                    //1707
//                    crawlerFinishQueueHandler.updateRank(bookView);
                    flushBookRankToDb.updateRank(bookView);
                }

            }

        } catch (Exception e) {
            System.err.println("errlr");
        }
    }

}
