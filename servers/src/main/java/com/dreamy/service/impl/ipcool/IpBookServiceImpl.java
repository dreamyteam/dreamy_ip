package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.IpBookDao;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.domain.ipcool.IpBookConditions;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class IpBookServiceImpl implements IpBookService {
    @Resource
    private IpBookDao ipBookDao;


    @Autowired
    private BookCrawlerInfoService bookCrawlerInfoService;


    @Override
    public IpBook saveRecordAndCrawlerInfo(IpBook ipBook, List<BookCrawlerInfo> list) {
        ipBookDao.save(ipBook);
        for (BookCrawlerInfo bookCrawlerInfo : list) {
            bookCrawlerInfo.status(1);
            bookCrawlerInfo.setBookId(ipBook.getId());
            bookCrawlerInfoService.save(bookCrawlerInfo);
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
        if (params.get("id") != null) {
            conditions.createCriteria().andIdLessThan((Integer) params.get("id"));
            params.remove("id");
        }
        conditions.createCriteria().addByMap(params);
        conditions.setOrderByClause("id asc");
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
            bookCrawlerInfo.setBookId(ipBook.getId());
            bookCrawlerInfo.status(CrawlerTaskStatusEnums.waitting.getStatus());

            Integer id = bookCrawlerInfo.getId();
            if (id > 0) {
                BookCrawlerInfo info = bookCrawlerInfoService.getById(id);
                if (info != null && !info.getUrl().equals(bookCrawlerInfo.getUrl())) {
                    bookCrawlerInfo.status(CrawlerTaskStatusEnums.starting.getStatus());
                    bookCrawlerInfoService.update(bookCrawlerInfo);
                }
            } else {
                bookCrawlerInfoService.save(bookCrawlerInfo);
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
            bookCrawlerInfoService.update(bookCrawlerInfo);
        }

        return 0;
    }

    @Override
    public Integer updateByRecord(IpBook ipBook) {
        return ipBookDao.update(ipBook);
    }


    @Override
    public void save(IpBook ipBook) {
        ipBookDao.save(ipBook);
    }
}
