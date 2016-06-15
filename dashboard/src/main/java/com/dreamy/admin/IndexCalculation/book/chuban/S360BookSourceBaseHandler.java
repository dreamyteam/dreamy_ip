package com.dreamy.admin.IndexCalculation.book.chuban;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.ChubanBookDataSourceEnums;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/6/15
 * Time: 下午1:47
 */
@Component
public class S360BookSourceBaseHandler extends ChubanBookSourceBaseHandler {

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private BookIndexDataService bookIndexDataService;

    @Override
    public Integer getHandlerId() {
        return ChubanBookDataSourceEnums.s360.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        Integer score = 0;
        List<BookIndexData> bookIndexDatas = bookIndexDataService.getByBookId(bookView.getBookId());
        if (CollectionUtils.isNotEmpty(bookIndexDatas)) {
            Double res = 1.0;
            for (BookIndexData bookIndexData : bookIndexDatas) {
                if (bookIndexData.getSource().equals(IndexSourceEnums.s360.getType())) {
                    if (bookIndexData.getOverviewJson() != null) {
                        String monthIndex = bookIndexData.getOverviewJson().getMonthIndex();
                        if (!monthIndex.equals("-")) {
                            res = Double.parseDouble(monthIndex);
                        }
                    }
                }
            }

            score = res.intValue();
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
            Double propagateIndex = Math.log10(KeyWordEnums.so.getPercent() * keyWord.getIndexNum()) * 1000;
            return propagateIndex.intValue();
        }

        return super.getPropagationIndex(bookView);
    }
}
