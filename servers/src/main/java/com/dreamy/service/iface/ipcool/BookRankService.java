package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookRankService {
    public void save(BookRank bookRank);

    public List<BookRank> getList(BookRank bookRank, Page page);
}
