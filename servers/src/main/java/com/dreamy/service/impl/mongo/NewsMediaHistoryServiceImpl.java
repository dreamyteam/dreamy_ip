package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.NewsMediaHistory;
import com.dreamy.mogodb.dao.NewsMediaHistoryDao;
import com.dreamy.service.iface.mongo.NewsMediaHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Service
public class NewsMediaHistoryServiceImpl implements NewsMediaHistoryService {
    @Autowired
    NewsMediaHistoryDao newsMediaHistoryDao;

    @Override
    public void updateInser(NewsMediaHistory newsMediaHistory) {
        newsMediaHistoryDao.updateInser(newsMediaHistory);
    }

    @Override
    public void saveByRecord(NewsMediaHistory newsMediaHistory) {
        newsMediaHistoryDao.save(newsMediaHistory);

    }

    @Override
    public List<NewsMediaHistory> getByBookId(Integer id) {
        return newsMediaHistoryDao.queryByBookId(id);
    }
}
