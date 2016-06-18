package com.dreamy.admin.IndexCalculation.book.net;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.NetBookDataSourceEnums;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 下午11:18
 */
public class ZhNetBookSourceBaseHandler extends NetBookSourceBaseHandler {
    @Override
    public Integer getHandlerId() {
        return NetBookDataSourceEnums.zh.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        return super.getHotIndex(bookView);
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
