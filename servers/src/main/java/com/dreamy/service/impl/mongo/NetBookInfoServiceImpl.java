package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.dao.NetBookInfoDao;
import com.dreamy.service.iface.mongo.NetBookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/6/1.
 */
@Service
public class NetBookInfoServiceImpl implements NetBookInfoService {

    @Resource
    private NetBookInfoDao netBookInfoDao;
    @Override
    public void updateInser(NetBookInfo netBookInfo) {
        netBookInfoDao.updateInser(netBookInfo);
    }

    @Override
    public void saveByRecord(NetBookInfo netBookInfo) {
        netBookInfoDao.save(netBookInfo);
    }

    @Override
    public NetBookInfo getById(Integer bookId) {
        return netBookInfoDao.queryById(bookId);
    }
}
