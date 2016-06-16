package com.dreamy.admin.IndexCalculation.book.chuban;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.ChubanBookDataSourceEnums;
import com.dreamy.enums.ChubanBookHotIndexExchangeEnums;
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
public class DdBookSourceBaseHandler extends ChubanBookSourceBaseHandler {

    @Autowired
    private BookScoreService bookScoreService;

    private CrawlerSourceEnums crawlerSourceEnums = CrawlerSourceEnums.dangdang;


    @Override
    public Integer getHandlerId() {
        return ChubanBookDataSourceEnums.dangdang.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        Integer score = super.getHotIndex(bookView);

        BookScore bookScore = bookScoreService.getByBookIdAndSource(bookView.getBookId(), crawlerSourceEnums.getType());
        if (bookScore != null) {
            Integer commentNum = bookScore.getCommentNum();
            if (commentNum != null && commentNum > 0) {
                Double tmp = commentNum * ChubanBookHotIndexExchangeEnums.dangdang.getNum();
                score = tmp.intValue();
            }
        }

        return score;
    }

    @Override
    public Integer getReputationIndex(BookView bookView) {
        BookScore bookScore = bookScoreService.getByBookIdAndSource(bookView.getBookId(), crawlerSourceEnums.getType());
        if (bookScore != null) {
            Double score = bookScore.getScore();
            if (score != null && score > 0.0) {
                Double tmp = crawlerSourceEnums.getPercent() * score;
                return tmp.intValue();
            }
        }

        return super.getReputationIndex(bookView);
    }

}
