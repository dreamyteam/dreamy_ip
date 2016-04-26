package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookRankDao;
import com.dreamy.domain.ipcool.BookRank;
import com.dreamy.domain.ipcool.BookRankConditions;
import com.dreamy.service.iface.ipcool.BookRankService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/26.
 */
@Service
public class BookRankServiceImpl implements BookRankService {
    @Resource
    BookRankDao bookRankDao;
    @Override
    public void save(BookRank bookRank) {
        bookRankDao.save(bookRank);
    }

    @Override
    public List<BookRank> getList(BookRank bookRank, Page page) {
        Map<String,Object> params= BeanUtils.toQueryMap(bookRank);
        BookRankConditions conditions=new BookRankConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null){
            page.setTotalNum(bookRankDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookRankDao.selectByExample(conditions);
    }
}
