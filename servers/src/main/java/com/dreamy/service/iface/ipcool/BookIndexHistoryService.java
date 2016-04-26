package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookIndexHistory;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookIndexHistoryService {

    public void save(BookIndexHistory bookIndexHistory);

    public List<BookIndexHistory> getList(BookIndexHistory bookIndexHistory, Page page);
}
