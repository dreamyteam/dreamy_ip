package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookScoreDao;
import com.dreamy.domain.ipcool.*;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.IndexSourceEnums;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.service.iface.ipcool.BookScoreService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.ipcool.PeopleChartService;
import com.dreamy.service.iface.mongo.BookIndexDataService;
import com.dreamy.utils.ArrayUtils;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */
@Service
public class BookScoreServiceImpl implements BookScoreService {
    @Resource
    private BookScoreDao bookScoreDao;

    @Autowired
    private BookIndexDataService bookIndexDataService;

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private PeopleChartService peopleChartService;

    @Override
    public void saveUpdate(BookScore bookScore) {

        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookScore.getBookId()).andSourceEqualTo(bookScore.getSource());
        List<BookScore> list = bookScoreDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(list)) {
            BookScore old = list.get(0);
            if (bookScore.getCommentNum() > 0) {
                old.commentNum(bookScore.getCommentNum());
            }
            if (bookScore.getScore() > 0) {
                old.score(bookScore.getScore());
            }
            if (bookScore.getSaleSort() > 0) {
                old.saleSort(bookScore.getSaleSort());
            }
            bookScoreDao.update(old);
        } else {
            bookScoreDao.save(bookScore);
        }


    }

    @Override
    public List<BookScore> getList(BookScore bookScore, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(bookScore);
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(bookScoreDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookScoreDao.selectByExample(conditions);
    }


    @Override
    public List<BookScore> getByBookId(Integer bookId) {
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);

        return bookScoreDao.selectByExample(conditions);
    }

    @Override
    public BookScore getByBookIdAndSource(Integer bookId, Integer source) {
        BookScoreConditions conditions = new BookScoreConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId).andSourceEqualTo(source);

        List<BookScore> bookScoreList = bookScoreDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(bookScoreList)) {
            return bookScoreList.get(0);
        }

        return null;
    }

}
