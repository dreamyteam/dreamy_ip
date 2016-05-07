package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.NewsMediaDao;
import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.domain.ipcool.NewsMediaConditions;
import com.dreamy.mapper.ipcool.NewsMediaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/5/6.
 */
@Repository("newsMediaDao")
public class NewsMediaDaoImpl extends BaseDaoImpl<NewsMedia,Integer,NewsMediaConditions>implements NewsMediaDao {
    @Resource
    private NewsMediaMapper newsMediaMapper;
    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(newsMediaMapper);

    }
}
