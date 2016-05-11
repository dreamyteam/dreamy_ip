package com.dreamy.test.ipcool;

import com.dreamy.admin.tasks.BookScoreTask;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.StringUtils;
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
    @Autowired
    BookScoreTask bookScoreTask;



    @Test
    public void score() {
        bookScoreTask.insertValue();
    }





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
                bookScore.commentNum(bookInfo.getCommentNum() != null ? Integer.valueOf(bookInfo.getCommentNum()) : 0);
                bookScore.saleSort(StringUtils.isNotEmpty(bookInfo.getSaleSort()) ? Integer.valueOf(bookInfo.getSaleSort().replace(",","")) : 0);
                if (bookInfo.getSource() == 1) {
                    bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore())? Double.valueOf(bookInfo.getScore()) * 20.0 : 0.0);
                } else if (bookInfo.getSource() == 2) {
                    bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore())?  Double.valueOf(bookInfo.getScore()) : 0.0);
                } else if (bookInfo.getSource() == 3) {
                    bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore())? Double.valueOf(bookInfo.getScore()) : 0.0);
                } else if (bookInfo.getSource() == 4) {
                    bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore())?  Double.valueOf(bookInfo.getScore()) * 10.0 : 0.0);
                }

                bookScoreService.saveUpdate(bookScore);

            }
        }


    }
    @Test
    public void insertValueOne() {

        List<BookInfo> bookInfos = bookInfoService.getListByIpId(2);
        BookScore bookScore = null;
        for (BookInfo bookInfo : bookInfos) {
            bookScore = new BookScore();
            bookScore.source(bookInfo.getSource());
            bookScore.status(0);
            bookScore.bookId(bookInfo.getIpId());
            bookScore.commentNum(bookInfo.getCommentNum() != null ? Integer.valueOf(bookInfo.getCommentNum()) : 0);
            bookScore.saleSort(StringUtils.isNotEmpty(bookInfo.getSaleSort()) ? Integer.valueOf(bookInfo.getSaleSort().replace(",", "")) : 0);
            if (bookInfo.getSource() == 1) {
                bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore()) ? Double.valueOf(bookInfo.getScore()) * 20.0 : 0.0);
            } else if (bookInfo.getSource() == 2) {
                bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore()) ? Double.valueOf(bookInfo.getScore()) : 0.0);
            } else if (bookInfo.getSource() == 3) {
                bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore()) ? Double.valueOf(bookInfo.getScore()) : 0.0);
            } else if (bookInfo.getSource() == 4) {
                bookScore.score(StringUtils.isNotEmpty(bookInfo.getScore()) ? Double.valueOf(bookInfo.getScore()) * 10.0 : 0.0);
            }
            bookScoreService.saveUpdate(bookScore);


        }
    }



}
