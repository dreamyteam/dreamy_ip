package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.KeyWordHistory;
import com.dreamy.mogodb.dao.KeyWordHistoryDao;
import com.dreamy.service.iface.mongo.KeyWordHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Service
public class KeyWordHistoryServiceImpl implements KeyWordHistoryService {

    @Autowired
    KeyWordHistoryDao keyWordHistoryDao;

    @Override
    public void updateInser(KeyWordHistory keyWordHistory) {
        keyWordHistoryDao.updateInser(keyWordHistory);
    }

    @Override
    public void saveByRecord(KeyWordHistory keyWordHistory) {
        keyWordHistoryDao.save(keyWordHistory);
    }

    @Override
    public List<KeyWordHistory> getByBookId(Integer id) {

        return keyWordHistoryDao.queryByBookId(id);
    }
}
