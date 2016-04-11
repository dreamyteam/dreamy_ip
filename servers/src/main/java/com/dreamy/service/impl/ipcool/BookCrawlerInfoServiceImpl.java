package com.dreamy.service.impl.ipcool;

import com.dreamy.dao.iface.ipcool.BookCrawlerInfoDao;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookCrawlerInfoConditions;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class BookCrawlerInfoServiceImpl implements BookCrawlerInfoService {
    @Resource
    private BookCrawlerInfoDao bookCrawlerInfoDao;
    @Override
    public BookCrawlerInfo save(BookCrawlerInfo info) {
        bookCrawlerInfoDao.save(info);
        return info;
    }

    @Override
    public List<BookCrawlerInfo> getBy(BookCrawlerInfo bookCrawlerInfo)
    {
        Map<String,Object> params= BeanUtils.toQueryMap(bookCrawlerInfo);
        BookCrawlerInfoConditions conditions=new BookCrawlerInfoConditions();
        conditions.createCriteria().addByMap(params);
        return bookCrawlerInfoDao.selectByExample(conditions);
    }

    @Override
    public int update(BookCrawlerInfo info) {
        return bookCrawlerInfoDao.update(info);
    }
}
