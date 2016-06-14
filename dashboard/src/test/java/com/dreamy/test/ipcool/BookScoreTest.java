package com.dreamy.test.ipcool;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/28.
 */
public class BookScoreTest extends BaseJunitTest {


    @Resource
    BookScoreService bookScoreService;

    @Resource
    private BookInfoService bookInfoService;
    @Resource
    IpBookService ipBookService;


    @Test
    public void insert() {
        BookScore bookScore = new BookScore();
        bookScore.source(CrawlerSourceEnums.jd.getType());
        bookScore.status(0);
        bookScore.score(80.0);
        bookScore.bookId(1);
        bookScoreService.saveUpdate(bookScore);
    }

    @Test
    public void insertValue() {
        List<IpBook> list = ipBookService.getIpBookList(new IpBook(), null);
        for (IpBook ipBook : list) {
            List<BookInfo> bookInfos = bookInfoService.getListByIpId(ipBook.getId());
            BookScore bookScore = null;
            for (BookInfo bookInfo : bookInfos) {
                bookScore = new BookScore();
                bookScore.source(bookInfo.getSource());
                bookScore.status(0);
                bookScore.bookId(bookInfo.getIpId());
                int type = bookInfo.getSource();
                bookScore.commentNum(bookInfo.getCommentNum() != null ? Integer.valueOf(bookInfo.getCommentNum()) : 0);
                bookScore.saleSort(bookInfo.getSaleSort() != null ? bookInfo.getSaleSort() : 0);
                if (type == CrawlerSourceEnums.amazon.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? Double.valueOf(bookInfo.getScore()) * 20.0 : 0.0);
                } else if (type == CrawlerSourceEnums.jd.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? Double.valueOf(bookInfo.getScore()) : 0.0);
                } else if (type == CrawlerSourceEnums.dangdang.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? Double.valueOf(bookInfo.getScore()) : 0.0);
                } else if (type == CrawlerSourceEnums.douban.getType()) {
                    bookScore.score(bookInfo.getScore() != null ? Double.valueOf(bookInfo.getScore()) * 10.0 : 0.0);
                }

                bookScoreService.saveUpdate(bookScore);

            }
        }


    }


}
