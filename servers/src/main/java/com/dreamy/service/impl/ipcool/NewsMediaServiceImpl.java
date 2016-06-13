package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.NewsMediaDao;
import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.domain.ipcool.NewsMediaConditions;
import com.dreamy.service.iface.ipcool.NewsMediaService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/6.
 */
@Service
public class NewsMediaServiceImpl implements NewsMediaService {
    @Resource
    private NewsMediaDao newsMediaDao;


    @Override
    public void save(NewsMedia newsMedia) {
        newsMediaDao.save(newsMedia);
    }

    @Override
    public Integer delByBookId(Integer bookId) {
        NewsMediaConditions conditions = new NewsMediaConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);
        return newsMediaDao.deleteByExample(conditions);
    }

    @Override
    public List<NewsMedia> getList(NewsMedia media, Page page) {
        Map<String, Object> params = BeanUtils.toQueryMap(media);
        NewsMediaConditions conditions = new NewsMediaConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(newsMediaDao.countByExample(conditions));
            conditions.setPage(page);
        }
        return newsMediaDao.selectByExample(conditions);
    }

    @Override
    public List<NewsMedia> getByBookIdAndSource(Integer bookId, Integer source) {
        NewsMediaConditions conditions = new NewsMediaConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId).andSourceEqualTo(source);
        return newsMediaDao.selectByExample(conditions);
    }


    @Override
    public void saveOrUpdate(NewsMedia newsMedia) {
        List<NewsMedia> list = getByBookIdAndSource(newsMedia.getBookId(), newsMedia.getSource());
        if (CollectionUtils.isNotEmpty(list)) {
            newsMedia.setId(list.get(0).getId());
            newsMediaDao.update(newsMedia);
        } else {
            save(newsMedia);
        }

    }
}
