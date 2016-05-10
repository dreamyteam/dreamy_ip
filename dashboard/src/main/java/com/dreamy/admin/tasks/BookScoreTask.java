package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyongxing on 16/5/9.
 *
 * 积分 排名
 */
@Component
public class BookScoreTask {

    @Resource
    BookScoreService bookScoreService;

    @Resource
    private BookInfoService bookInfoService;
    @Resource
    IpBookService ipBookService;


    public void insertValue() {
        IpBook entity = new IpBook();
        entity.setType(1);
        int currentPage = 1;
        while (true) {
            Page page = new Page();
            page.setPageSize(100);
            page.setCurrentPage(currentPage);
            List<IpBook> list = ipBookService.getIpBookList(entity, page);
            for (IpBook ipBook : list) {
                List<BookInfo> bookInfos = bookInfoService.getListByIpId(ipBook.getId());
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
            if (!page.isHasNextPage()) {
                break;
            }
            currentPage++;

        }


    }
}
