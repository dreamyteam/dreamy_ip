package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookRank;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookRankService {
    /**
     *
     * @param bookRank
     */
    public void save(BookRank bookRank);

    /**
     *
     * @param bookRank
     * @param page
     * @return
     */
    public List<BookRank> getList(BookRank bookRank, Page page);

    /**
     *
     * @param redisSetResult
     * @return
     */
    Map<Integer, Integer> getCompositeRankMap();
}
