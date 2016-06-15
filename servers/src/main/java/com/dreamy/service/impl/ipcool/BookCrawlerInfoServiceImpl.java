package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookCrawlerInfoDao;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookCrawlerInfoConditions;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class BookCrawlerInfoServiceImpl implements BookCrawlerInfoService {
    @Resource
    private BookCrawlerInfoDao bookCrawlerInfoDao;

    @Autowired
    private QueueService queueService;
    @Value("${queue_jd_crawler}")
    private String queueNameJd;

    @Value("${queue_amazon_crawler}")
    private String queueNameAmazon;

    @Value("${queue_dangdang_crawler}")
    private String queueNameDangDang;

    @Value("${queue_douban_comment}")
    private String commentQueueName;

    @Override
    public BookCrawlerInfo save(BookCrawlerInfo info) {
        BookCrawlerInfo entity = new BookCrawlerInfo();
        entity.setBookId(info.getBookId());
        entity.setSource(info.getSource());
        List<BookCrawlerInfo> list = getByRecord(entity);
        if (CollectionUtils.isEmpty(list)) {
            bookCrawlerInfoDao.save(info);
        }
        else{
            BookCrawlerInfo old=list.get(0);
            info.setId(old.getId());
            bookCrawlerInfoDao.update(info);
        }
        return info;
    }

    private void push(String isbn,Integer bookId,String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isbn", isbn);
        map.put("url", url);
        map.put("operation", OperationEnums.crawler.getCode());
        queueService.push(queueNameJd, map);
        queueService.push(queueNameAmazon, map);
        queueService.push(queueNameDangDang, map);
        queueService.push(commentQueueName, map);

    }

    @Override
    public List<BookCrawlerInfo> getByRecord(BookCrawlerInfo bookCrawlerInfo) {
        Map<String, Object> params = BeanUtils.toQueryMap(bookCrawlerInfo);
        BookCrawlerInfoConditions conditions = new BookCrawlerInfoConditions();
        conditions.createCriteria().addByMap(params);
        return bookCrawlerInfoDao.selectByExample(conditions);
    }

    @Override
    public List<BookCrawlerInfo> getListByRecord(BookCrawlerInfo bookCrawlerInfo, Page page) {
        BookCrawlerInfoConditions conditions = new BookCrawlerInfoConditions();
        if (bookCrawlerInfo != null) {
            Map<String, Object> params = BeanUtils.toQueryMap(bookCrawlerInfo);
            conditions.createCriteria().addByMap(params);
        }
        if (page != null) {
            page.setTotalNum(bookCrawlerInfoDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return bookCrawlerInfoDao.selectByExample(conditions);
    }

    @Override
    public int update(BookCrawlerInfo info) {
        return bookCrawlerInfoDao.update(info);
    }

    @Override
    public BookCrawlerInfo getById(Integer id) {
        return bookCrawlerInfoDao.selectById(id);
    }

    @Override
    public List<BookCrawlerInfo> getByPageAndOrder(Page page, String order) {
        BookCrawlerInfoConditions conditions = new BookCrawlerInfoConditions();
        conditions.setPage(page);
        conditions.setOrderByClause(order);
        return bookCrawlerInfoDao.selectByExample(conditions);
    }

    @Override
    public List<BookCrawlerInfo> getByCondition(BookCrawlerInfoConditions conditions) {
        return bookCrawlerInfoDao.selectByExample(conditions);
    }
}
