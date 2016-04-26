package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookViewDao;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.BookViewConditions;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.BeanUtils;
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
        Map<String,Object> params= BeanUtils.toQueryMap(bookView);
        BookViewConditions conditions=new BookViewConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null){
            page.setTotalNum(bookViewDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookViewDao.selectByExample(conditions);
    }

    @Override
    public BookView getById(Integer id) {
        return bookViewDao.selectById(id);
    }
}