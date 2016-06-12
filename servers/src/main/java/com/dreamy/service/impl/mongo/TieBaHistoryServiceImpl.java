package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.tieba.TieBaHistory;
import com.dreamy.mogodb.dao.TieBaHistoryDao;
import com.dreamy.service.iface.mongo.TieBaHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyongxing on 16/6/12.
 */
@Service
public class TieBaHistoryServiceImpl implements TieBaHistoryService {

    @Autowired
    private TieBaHistoryDao tieBaHistoryDao;

    @Override
    public void updateInser(TieBaHistory tieBaHistory) {
        tieBaHistoryDao.updateInser(tieBaHistory);
    }

    @Override
    public void saveByRecord(TieBaHistory tieBaHistory) {
        tieBaHistoryDao.save(tieBaHistory);
    }

    @Override
    public List<TieBaHistory> getByBookId(Integer bookId) {
        return tieBaHistoryDao.queryByBookId(bookId);
    }
}
