package com.dreamy.admin.IndexCalculation.book.net;

import com.dreamy.admin.IndexCalculation.book.BookSourceBaseHandler;
import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.CrawlerSourceEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午1:45
 */
@Component
public abstract class NetBookSourceBaseHandler implements BookSourceBaseHandler {

    @Autowired
    NetManage netManage;

    @PostConstruct
    public void init() {
        netManage.register(this);
    }

    @Override
    public Integer getHandlerId() {
        return 0;
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        return 1;
    }

    @Override
    public Integer getPropagationIndex(BookView bookView) {
        return 1;
    }

    @Override
    public Integer getActiveIndex(BookView bookView) {
        return null;
    }

    @Override
    public Integer getReputationIndex(BookView bookView) {
        return 1;
    }

    @Override
    public Integer getDevelopIndex(BookView bookView) {
        return 1;
    }


    public Map<Integer, Double> getPercentMap(List<BookScore> bookScores) {
        Map<Integer, Double> percentMap = new HashMap<>();
        Map<Integer, Double> map = new HashMap<>();

        for (CrawlerSourceEnums sourceEnums : CrawlerSourceEnums.values()) {
            map.put(sourceEnums.getType(), sourceEnums.getPercent());
        }

        if (bookScores.size() != 4) {
            Double totalPercent = 0.0;
            for (BookScore bookScore : bookScores) {
                totalPercent += map.get(bookScore.getSource());
            }

            for (BookScore bookScore : bookScores) {
                percentMap.put(bookScore.getSource(), map.get(bookScore.getSource()) / totalPercent);
            }
        } else {
            percentMap = map;
        }

        return percentMap;
    }
}
