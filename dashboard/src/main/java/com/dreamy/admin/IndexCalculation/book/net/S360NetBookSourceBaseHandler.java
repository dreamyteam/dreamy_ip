package com.dreamy.admin.IndexCalculation.book.net;

import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.*;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.utils.CollectionUtils;
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
public class S360NetBookSourceBaseHandler extends NetBookSourceBaseHandler {
    @Autowired
    private BookIndexDataService bookIndexDataService;

    @Autowired
    private KeyWordService keyWordService;

    @Override
    public Integer getHandlerId() {
        return NetBookDataSourceEnums.s360.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {

        Integer score = 0;
        List<BookIndexData> bookIndexDatas = bookIndexDataService.getByBookId(bookView.getBookId());
        if (CollectionUtils.isNotEmpty(bookIndexDatas)) {
            for (BookIndexData bookIndexData : bookIndexDatas) {
                if (bookIndexData.getSource().equals(IndexSourceEnums.s360.getType())) {
                    if (bookIndexData.getOverviewJson() != null) {
                        Integer monthIndex = bookIndexData.getIndex();
                        if (!monthIndex.equals("-")) {
                            score = monthIndex;
                        }
                    }
                }
            }

        }

        if (score == 0) {
            score = super.getHotIndex(bookView);
        }

        return score;
    }

    @Override
    public Integer getPropagationIndex(BookView bookView) {
        KeyWord keyWord = keyWordService.getByBookIdAndSource(bookView.getBookId(), KeyWordEnums.so.getType());
        if (keyWord != null) {
            Double propagateIndex = NetBookPropagationIndexExchangeEnums.s360.getNum() * keyWord.getIndexNum();
            return propagateIndex.intValue();
        }

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
