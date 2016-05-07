package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookIndexHistoryDao;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookIndexHistoryConditions;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<BookIndexHistory> getList(BookIndexHistory bookIndexHistory, Page page,String orderBy) {
        Map<String,Object> params= BeanUtils.toQueryMap(bookIndexHistory);
        BookIndexHistoryConditions conditions= new BookIndexHistoryConditions();
        BookIndexHistoryConditions.Criteria criteria = conditions.createCriteria();
        if (params.containsKey("created_at")) {
            criteria.andCreatedAtGreaterThanOrEqualTo((Date) params.get("created_at"));
            params.remove("created_at");
        }
        if (params.containsKey("updated_at")) {
            criteria.andCreatedAtLessThanOrEqualTo((Date) params.get("updated_at"));
            params.remove("updated_at");
        }
        criteria.addByMap(params);
        if(page!=null)
        {
            page.setTotalNum(bookIndexHistoryDao.countByExample(conditions));
            conditions.setPage(page);
        }
        if (StringUtils.isNotEmpty(orderBy)){
            conditions.setOrderByClause(orderBy);
        }
        else{
            conditions.setOrderByClause("id");
        }

        return bookIndexHistoryDao.selectByExample(conditions);
    }

    @Override
    public BookIndexHistory getMaxByBookId(Integer bookId) {
       return bookIndexHistoryDao.selectMaxByBookId(bookId);
    }
}
