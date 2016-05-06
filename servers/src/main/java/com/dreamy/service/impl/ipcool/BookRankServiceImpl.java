package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookRankDao;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankConditions;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.mogodb.beans.Book;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import com.mchange.v1.util.ListUtils;
import com.mchange.v1.util.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookRankServiceImpl implements BookRankService {
    @Resource
    BookRankDao bookRankDao;

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private BookViewService bookViewService;

    @Override
    public void save(BookRank bookRank) {
        bookRankDao.save(bookRank);
    }

    @Override
    public List<BookRank> getList(BookRank bookRank, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(bookRank);
        BookRankConditions conditions = new BookRankConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(bookRankDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return bookRankDao.selectByExample(conditions);
    }

    @Override
    public List<BookRank> getListByBookIds(List<Integer> bookIds) {


        return null;
    }

    @Override
    public Map<Integer, Integer> getBookRankMapFromRedisByCacheKey(String cacheKey) {
        Map<Integer, Integer> map = new HashMap<>();

        Set<Object> redisSetResult = redisClientService.zrange(cacheKey, 0, -1);
        if (CollectionUtils.isNotEmpty(redisSetResult)) {
            Integer i = 1;
            for (Object bookId : redisSetResult) {
                map.put(Integer.parseInt(bookId.toString()), i);
                i++;
            }
        }

        return map;
    }

    @Override
    public Map<Integer, Integer> getCompositeRankMapByBookIds(List<Integer> bookIds) {
        Map<Integer, Integer> map = new HashMap<>();

        if (CollectionUtils.isNotEmpty(bookIds)) {
            BookRankConditions bookRankConditions = new BookRankConditions();
            bookRankConditions.createCriteria().andBookIdIn(bookIds);

            List<BookRank> bookRanks = bookRankDao.selectByExample(bookRankConditions);
            if (CollectionUtils.isNotEmpty(bookRanks)) {
                for (BookRank bookRank : bookRanks) {
                    map.put(bookRank.getBookId(), bookRank.getRank());
                }
            }
        }

        return map;
    }

    @Override
    public Integer deleteById(Integer id) {
        return bookRankDao.deleteById(id);
    }

    @Override
    public Integer deleteByBookId(Integer bookId) {
        BookRankConditions conditions = new BookRankConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);
        return bookRankDao.deleteByExample(conditions);
    }
}
