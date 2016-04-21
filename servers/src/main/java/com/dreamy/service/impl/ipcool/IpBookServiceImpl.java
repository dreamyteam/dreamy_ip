package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.BookCrawlerInfoDao;
import com.dreamy.dao.iface.ipcool.IpBookDao;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.domain.ipcool.IpBookConditions;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.QueueRoutingKey;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class IpBookServiceImpl implements IpBookService {
    @Resource
    private IpBookDao ipBookDao;
    @Resource
    private BookCrawlerInfoDao bookCrawlerInfoDao;

    @Resource
    private QueueService queueService;

    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;

    @Override
    public IpBook saveRecordAndCrawlerInfo(IpBook ipBook, List<BookCrawlerInfo> list) {
        ipBookDao.save(ipBook);
        for (BookCrawlerInfo bookCrawlerInfo : list) {
            bookCrawlerInfo.status(1);
            bookCrawlerInfo.setBookId(ipBook.getId());
            bookCrawlerInfoDao.save(bookCrawlerInfo);
        }
        return ipBook;
    }

    @Override
    public IpBook getById(Integer id) {
        return ipBookDao.selectById(id);
    }

    @Override
    public List<IpBook> getIpBookList(IpBook ipBook, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(ipBook);
        IpBookConditions conditions = new IpBookConditions();

        conditions.createCriteria().addByMap(params);
        conditions.setOrderByClause("id desc");
        if (page != null) {
            int row = ipBookDao.countByExample(conditions);
            page.setTotalNum(row);
            conditions.setPage(page);

        }
        List<IpBook> list = ipBookDao.selectByExample(conditions);
        return list;
    }

    @Override
    public int updateRecordAndCrawlerInfo(IpBook ipBook, List<BookCrawlerInfo> list) {
        for (BookCrawlerInfo bookCrawlerInfo : list) {
            bookCrawlerInfo.status(1);
            bookCrawlerInfo.setBookId(ipBook.getId());
            if (bookCrawlerInfo.getId() > 0) {
                bookCrawlerInfoDao.update(bookCrawlerInfo);
            } else {
                bookCrawlerInfoDao.save(bookCrawlerInfo);
            }
        }
        return ipBookDao.update(ipBook);
    }

    @Override
    public int delByIds(List<Integer> ids) {
        for (Integer id : ids) {
            IpBook ipBook = new IpBook().status(-1).id(id);
            ipBookDao.update(ipBook);
            BookCrawlerInfo bookCrawlerInfo = new BookCrawlerInfo().bookId(id).status(-1);
            bookCrawlerInfoDao.update(bookCrawlerInfo);
        }

        return 0;
    }

    @Override
    public Integer updateByRecord(IpBook ipBook) {
        return ipBookDao.update(ipBook);
    }

    @Override
    public void doCrawler(BookCrawlerInfo info) {
        if (StringUtils.isNotEmpty(info.getUrl())) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", info.getSource());
            map.put("url", info.getUrl());
            map.put("ipId", info.getBookId());
            map.put("crawlerId", info.getId());
            queueService.push(QueueRoutingKey.CRAWLER_EVENT, map);

            if (info.getSource().equals(CrawlerSourceEnums.douban.getType())) {
                queueService.push(QueueRoutingKey.CRAWLER_COMMENT, map);
            }

            info.status(CrawlerTaskStatusEnums.starting.getStatus());
            bookCrawlerInfoService.update(info);
        } else {
            info.status(CrawlerTaskStatusEnums.finished.getStatus());
            bookCrawlerInfoService.update(info);
        }
    }
}
