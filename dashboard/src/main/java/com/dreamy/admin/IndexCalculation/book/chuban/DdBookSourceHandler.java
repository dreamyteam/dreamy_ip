package com.dreamy.admin.IndexCalculation.book.chuban;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.service.iface.ipcool.BookScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午1:47
 */
@Component
public class DdBookSourceHandler extends ChubanBookSourceHandler {

    @Autowired
    private BookScoreService bookScoreService;

    private CrawlerSourceEnums crawlerSourceEnums=CrawlerSourceEnums.dangdang;


    @Override
    public Integer getHandlerId() {
        return crawlerSourceEnums.getType();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        Integer score = super.getHotIndex(bookView);

        BookScore bookScore = bookScoreService.getByBookIdAndSource(bookView.getBookId(), crawlerSourceEnums.getType());
        if (bookScore != null) {
            Integer commentNum = bookScore.getCommentNum();
            if (commentNum != null && commentNum > 0) {
                Double tmp = commentNum * crawlerSourceEnums.getPercent();
                score = tmp.intValue();
            }
        }

        return score;
    }

}
