package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookRankHistoryDao;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookRankHistoryConditions;
import com.dreamy.service.iface.ipcool.BookRankHistoryService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookRankHistoryServiceImpl implements BookRankHistoryService {
    @Resource
    BookRankHistoryDao bookRankHistoryDao;
    @Override
    public void save(BookRankHistory bookRankHistory) {
        bookRankHistoryDao.save(bookRankHistory);
    }

    @Override
    public List<BookRankHistory> getList(BookRankHistory bookRankHistory, Page page) {

        Map<String,Object> params= BeanUtils.toQueryMap(bookRankHistory);
        BookRankHistoryConditions conditions=new BookRankHistoryConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null){
            page.setTotalNum(bookRankHistoryDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookRankHistoryDao.selectByExample(conditions);
    }
}
