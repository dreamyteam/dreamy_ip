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
     * @param bookRank
     */
    void save(BookRank bookRank);

    /**
     * @param bookRank
     * @param page
     * @return
     */
    List<BookRank> getList(BookRank bookRank, Page page);

    /**
     *
     * @param bookIds
     * @return
     */
    List<BookRank> getListByBookIds(List<Integer> bookIds);

    /**
     * 从redis中获取排行榜相关的内容
     *
     * @return
     */
    Map<Integer, Integer> getBookRankMapFromRedisByCacheKey(String cacheKey);



    /**
     * @param bookIds
     * @return
     */
    Map<Integer, Integer> getCompositeRankMapByBookIds(List<Integer> bookIds);

    /**
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     *
     * @param bookId
     * @return
     */
    Integer deleteByBookId(Integer bookId);
}
