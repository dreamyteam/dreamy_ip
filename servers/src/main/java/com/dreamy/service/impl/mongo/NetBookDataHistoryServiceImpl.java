package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.history.NetBookDataHistory;
import com.dreamy.mogodb.dao.NetBookDataHistoryDao;
import com.dreamy.service.iface.mongo.NetBookDataHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Service
public class NetBookDataHistoryServiceImpl implements NetBookDataHistoryService {

    @Autowired
    NetBookDataHistoryDao netBookDataHistoryDao;
    @Override
    public void updateInser(NetBookDataHistory netBookDataHistory) {
        netBookDataHistoryDao.updateInser(netBookDataHistory);
    }

    @Override
    public void saveByRecord(NetBookDataHistory netBookDataHistory) {
        netBookDataHistoryDao.save(netBookDataHistory);
    }

    @Override
    public List<NetBookDataHistory> getByBookId(Integer bookId) {
        return netBookDataHistoryDao.queryByBookId(bookId);
    }
}
