package com.dreamy.admin.IndexCalculation.book.net;

import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.NetBookDataSourceEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 下午11:18
 */
@Component
public class ZhNetBookSourceBaseHandler extends NetBookSourceBaseHandler {

    @Autowired
    private BookCrawlerInfoService crawlerInfoService;

    @Autowired
    private NetBookInfoService netBookInfoService;

    @Override
    public Integer getHandlerId() {
        return NetBookDataSourceEnums.zh.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        return 0;
//        BookCrawlerInfo bookCrawlerInfo = crawlerInfoService.getByBookIdAndType(bookView.getBookId(), CrawlerSourceEnums.zongheng.getType());
//        if (bookCrawlerInfo == null) {
//            return 0;
//        }
//
//        NetBookInfo netBookInfo = netBookInfoService.getById(bookView.getBookId());
//        if (netBookInfo != null) {
//            Integer totalClick = netBookInfo.getClickNum();
//            Integer totalRecommendNum = netBookInfo.getRecommendNum();
//
//            if (totalClick == null || totalClick < 0) {
//                totalClick = 1;
//            }
//
//            if (totalRecommendNum == null || totalRecommendNum < 0) {
//                totalRecommendNum = 1;
//            }
//
//            Double temp = (totalClick / 3000.0 + totalRecommendNum / 300.0);
//
//            return temp.intValue();
//        }
//
//        return super.getHotIndex(bookView);
    }

    @Override
    public Integer getPropagationIndex(BookView bookView) {
        return super.getPropagationIndex(bookView);
    }

    @Override
    public Integer getActiveIndex(BookView bookView) {
        return super.getActiveIndex(bookView);
    }

    @Override
    public Integer getReputationIndex(BookView bookView) {
        return super.getReputationIndex(bookView);
    }

    @Override
    public Integer getDevelopIndex(BookView bookView) {
        return super.getDevelopIndex(bookView);
    }

    @Override
    public Map<Integer, Double> getPercentMap(List<BookScore> bookScores) {
        return super.getPercentMap(bookScores);
    }
}
