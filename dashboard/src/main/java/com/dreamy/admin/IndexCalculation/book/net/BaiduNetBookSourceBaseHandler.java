package com.dreamy.admin.IndexCalculation.book.net;

import com.dreamy.domain.ipcool.BookScore;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.*;
import com.dreamy.service.iface.ipcool.KeyWordService;
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
public class BaiduNetBookSourceBaseHandler extends NetBookSourceBaseHandler {

    @Autowired
    private KeyWordService keyWordService;

    @Override
    public Integer getHandlerId() {
        return NetBookDataSourceEnums.baidu.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        return super.getHotIndex(bookView);
    }

    @Override
    public Integer getPropagationIndex(BookView bookView) {
        KeyWord keyWord = keyWordService.getByBookIdAndSource(bookView.getBookId(), KeyWordEnums.baidu.getType());
        if (keyWord != null) {
            Double propagateIndex = NetBookPropagationIndexExchangeEnums.baidu.getNum() * keyWord.getIndexNum();
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
