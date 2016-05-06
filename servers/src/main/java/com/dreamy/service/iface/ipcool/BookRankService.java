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
     * 根据排行榜的名词获取前后指定名次的详细信息
     *
     * @param
     * @return
     */
    Map<String, Object> getRankPositionAndDetailByBookIdAndType(Integer rankId, Integer rankType);

    /**
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
     * @param bookId
     * @return
     */
    Integer deleteByBookId(Integer bookId);

    /**
     *
     * @param position
     * @param totalNum
     * @return
     */
    Integer getRankClassByPosition(Integer position, Integer totalNum);
}
