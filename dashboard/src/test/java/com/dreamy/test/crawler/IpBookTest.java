package com.dreamy.test.crawler;

import com.dreamy.admin.tasks.rank.CreateRankTask;
import com.dreamy.admin.tasks.rank.FlushBookRankToDb;
import com.dreamy.admin.tasks.rank.UpdateIndexTask;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.CollectionUtils;
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
    private UpdateIndexTask updateIndexTask;

    @Autowired
    private QueueService queueService;

    @Autowired
    private FlushBookRankToDb flushBookRankToDb;

    @Autowired
    private CreateRankTask createRankTask;

    @Autowired
    private RedisClientService redisClientService;

    @Value("${queue_crawler_over}")
    private String BookOverQueue;

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
        bookInfo.setSaleSort("12");
        bookInfo.setInfo("adadads");
        bookInfoService.updateInser(bookInfo);
    }

    @Test
    public void developIndex() {
//        BookView bookView = bookViewService.getByBookId(4769);
//        String index = bookScoreService.getPropagateIndexByBookId(4769);
//
//        System.err.println(index);

//        int currentPage = 1;
//        Page page = new Page();
//        page.setPageSize(100);
//        Boolean isLoop = true;
//
//        while (isLoop) {
//            page.setCurrentPage(currentPage);
//            List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", BookTypeEnums.chuban.getType());
//            if (CollectionUtils.isNotEmpty(bookViewList)) {
//                for (BookView bookView : bookViewList) {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("bookId", "" + bookView.getBookId());
//                    queueService.push(BookOverQueue, params);
//                }
//                currentPage++;
//            } else {
//                isLoop = false;
//            }
//        }

//        createRankTask.run();
//        flushBookRankToDb.run();

        BookView bookView = bookViewService.getById(5837);
        Long rankNum = redisClientService.reverseZrank(BookRankEnums.composite.getCacheKey(), bookView.getBookId().toString());

        System.err.println(rankNum);
    }

}
