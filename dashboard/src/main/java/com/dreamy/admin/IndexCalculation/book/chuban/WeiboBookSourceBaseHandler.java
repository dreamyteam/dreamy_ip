package com.dreamy.admin.IndexCalculation.book.chuban;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.ChubanBookDataSourceEnums;
import com.dreamy.enums.ChubanBookHotIndexExchangeEnums;
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
public class WeiboBookSourceBaseHandler extends ChubanBookSourceBaseHandler {

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private BookIndexDataService bookIndexDataService;

    @Override
    public Integer getHandlerId() {
        return ChubanBookDataSourceEnums.weibo.getSource();
    }

    @Override
    public Integer getHotIndex(BookView bookView) {
        Integer score = 0;
        List<BookIndexData> bookIndexDatas = bookIndexDataService.getByBookId(bookView.getBookId());
        if (CollectionUtils.isNotEmpty(bookIndexDatas)) {
            Double res = 1.0;
            for (BookIndexData bookIndexData : bookIndexDatas) {
                if (bookIndexData.getSource().equals(IndexSourceEnums.weibo.getType())) {
                    if (bookIndexData.getOverviewJson() != null) {
                        Integer monthIndex = bookIndexData.getIndex();
                        if (!monthIndex.equals("-")) {
                            res = monthIndex * ChubanBookHotIndexExchangeEnums.weibo.getNum();
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

        KeyWord keyWord = keyWordService.getByBookIdAndSource(bookView.getBookId(), KeyWordEnums.weibo.getType());
        if (keyWord != null) {
            Double propagateIndex = Math.log10(KeyWordEnums.weibo.getPercent() * keyWord.getIndexNum()) * 1000;
            return propagateIndex.intValue();
        }

        return super.getPropagationIndex(bookView);
    }
}
