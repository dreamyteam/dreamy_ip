package com.dreamy.admin.IndexCalculation.book.chuban;

import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.ChubanBookDataSourceEnums;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.service.iface.ipcool.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public Integer getHandlerId() {
        return ChubanBookDataSourceEnums.weibo.getSource();
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
