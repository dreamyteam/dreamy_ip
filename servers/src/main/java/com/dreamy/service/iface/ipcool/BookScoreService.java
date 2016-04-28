package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookScore;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/28.
 */
public interface BookScoreService {
    public void save(BookScore bookScore);

    public List<BookScore> getList(BookScore bookScore, Page page);

}
