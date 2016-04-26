package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRankHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookRankHistoryService {

    public void save(BookRankHistory bookRankHistory);

    public List<BookRankHistory> getList(BookRankHistory bookRankHistory, Page page);
}
