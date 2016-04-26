package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookIndexHistoryDao;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookIndexHistoryConditions;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookIndexHistoryServiceImpl implements BookIndexHistoryService {
    @Resource
    BookIndexHistoryDao bookIndexHistoryDao;
    @Override
    public void save(BookIndexHistory bookIndexHistory) {
        bookIndexHistoryDao.save(bookIndexHistory);
    }

    @Override
    public List<BookIndexHistory> getList(BookIndexHistory bookIndexHistory, Page page) {
        Map<String,Object> params= BeanUtils.toQueryMap(bookIndexHistory);
        BookIndexHistoryConditions conditions= new BookIndexHistoryConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null)
        {
            page.setTotalNum(bookIndexHistoryDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookIndexHistoryDao.selectByExample(conditions);
    }
}
