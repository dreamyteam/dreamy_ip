package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookViewDao;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.BookViewConditions;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookViewServiceImpl implements BookViewService {
    @Resource
    private BookViewDao bookViewDao;

    @Override
    public void save(BookView bookView) {
        bookViewDao.save(bookView);
    }

    @Override
    public Integer update(BookView bookView) {
        return bookViewDao.update(bookView);
    }

    @Override
    public List<BookView> getList(BookView bookView, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(bookView);
        BookViewConditions conditions = new BookViewConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(bookViewDao.countByExample(conditions));
            conditions.setPage(page);
            conditions.setOrderByClause("hot_index desc");
        }
        return bookViewDao.selectByExample(conditions);
    }

    @Override
    public BookView getById(Integer id) {
        return bookViewDao.selectById(id);
    }

    @Override
    public List<BookView> getListByPageAndOrder(Page page, String order) {
        BookViewConditions conditions = new BookViewConditions();
        conditions.setPage(page);
        conditions.setOrderByClause(order);
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

        return new BookView();
    }
}
