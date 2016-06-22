package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookViewCalculateResultDao;
import com.dreamy.dao.iface.ipcool.BookViewDao;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.BookViewCalculateResult;
import com.dreamy.domain.ipcool.BookViewCalculateResultConditions;
import com.dreamy.domain.ipcool.BookViewConditions;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookViewServiceImpl implements BookViewService {
    @Resource
    private BookViewDao bookViewDao;

    @Resource
    private BookViewCalculateResultDao bookViewCalculateResultDao;

    @Override
    public void save(BookView bookView) {
        bookViewDao.save(bookView);
    }

    @Override
    public void saveCalculateRes(BookViewCalculateResult result) {
        BookViewCalculateResultConditions conditions = new BookViewCalculateResultConditions();
        conditions.createCriteria().andBookIdEqualTo(result.getBookId());
        List<BookViewCalculateResult> bookViewCalculateResultConditionsList = bookViewCalculateResultDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(bookViewCalculateResultConditionsList)) {
            BookViewCalculateResult temp = bookViewCalculateResultConditionsList.get(0);
            result.id(temp.getId());
            bookViewCalculateResultDao.update(result);
        }
        bookViewCalculateResultDao.save(result);
    }

    @Override
    public Integer update(BookView bookView) {
        return bookViewDao.update(bookView);
    }

    @Override
    public Integer updateCalculateRes(BookViewCalculateResult result) {
        return bookViewCalculateResultDao.update(result);
    }


    @Override
    public List<BookView> getList(BookView bookView, Page page, String order) {
        Map<String, Object> params = BeanUtils.toQueryMap(bookView);
        BookViewConditions conditions = new BookViewConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(bookViewDao.countByExample(conditions));
            conditions.setPage(page);
        }
        conditions.setOrderByClause("book_id  desc");
        if (StringUtils.isNotEmpty(order)) {
            conditions.setOrderByClause(order);
        }
        return bookViewDao.selectByExample(conditions);
    }

    @Override
    public Integer getTotalCountByType(Integer type) {
        BookViewConditions conditions = new BookViewConditions();
        BookViewConditions.Criteria criteria = conditions.createCriteria();
        criteria.andIdGreaterThan(0);
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }

        return bookViewDao.countByExample(conditions);
    }

    @Override
    public BookView getById(Integer id) {
        return bookViewDao.selectById(id);
    }


    @Override
    public BookViewCalculateResult getCalculateResByBookId(Integer bookId) {
        BookViewCalculateResultConditions conditions = new BookViewCalculateResultConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);

        List<BookViewCalculateResult> bookViewCalculateResultList = bookViewCalculateResultDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(bookViewCalculateResultList)) {
            return bookViewCalculateResultList.get(0);
        }

        return null;
    }

    @Override
    public List<BookViewCalculateResult> getCalculateResByPageAndOrder(Page page, String order) {
        BookViewCalculateResultConditions conditions = new BookViewCalculateResultConditions();
        conditions.setOrderByClause(order);
        conditions.setPage(page);

        return bookViewCalculateResultDao.selectByExample(conditions);
    }

    @Override
    public List<BookView> getListByBookIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        BookViewConditions conditions = new BookViewConditions();
        conditions.createCriteria().andBookIdIn(ids);

        return bookViewDao.selectByExample(conditions);
    }

    @Override
    public List<BookView> getListByPageAndOrderAndType(Page page, String order, Integer type) {
        BookViewConditions conditions = new BookViewConditions();
        if (type != null) {
            conditions.createCriteria().andTypeEqualTo(type);
        }
        conditions.setPage(page);
        conditions.setOrderByClause(order);

        if (page != null) {
            page.setTotalNum(bookViewDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return bookViewDao.selectByExample(conditions);
    }

    @Override
    public BookView getByBookId(Integer bookId) {
        BookViewConditions conditions = new BookViewConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);

        Page pa = new Page();
        pa.setPageSize(1);
        conditions.setPage(pa);

        List<BookView> bookViews = bookViewDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(bookViews)) {
            return bookViews.get(0);
        }

        return null;
    }


    @Override
    public Map<Integer, BookView> getListMapByBookIds(List<Integer> ids) {
        List<BookView> bookViewList = getListByBookIds(ids);
        if (CollectionUtils.isNotEmpty(bookViewList)) {
            Map<Integer, BookView> map = new HashMap<>();
            for (BookView bookView : bookViewList) {
                map.put(bookView.getBookId(), bookView);
            }

            return map;
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        bookViewDao.deleteById(id);
    }
}
