package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.mogodb.beans.tieba.TieBaHistory;
import com.dreamy.mogodb.dao.TieBaDao;
import com.dreamy.service.iface.mongo.TieBaHistoryService;
import com.dreamy.service.iface.mongo.TieBaService;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wangyongxing on 16/6/12.
 * 百度贴吧
 */
@Service
public class TieBaServiceImpl implements TieBaService {

    @Autowired
    private TieBaDao tieBaDao;
    @Autowired
    TieBaHistoryService tieBaHistoryService;

    @Override
    public void updateInser(TieBa tieBa) {
        tieBaDao.updateInser(tieBa);
        TieBaHistory tieBaHistory = new TieBaHistory();
        tieBaHistory.setPopularitySort(tieBa.getPopularitySort());
        tieBaHistory.setFollowNum(tieBa.getFollowNum());
        tieBaHistory.setPostNum(tieBa.getPostNum());
        tieBaHistory.setDate(TimeUtils.toString(null, new Date()));
        tieBaHistory.setBookId(tieBa.getBookId());
        tieBaHistory.setId(tieBa.getBookId() + "-" + TimeUtils.toString(null, new Date()));
        tieBaHistoryService.updateInser(tieBaHistory);

    }

    @Override
    public void saveByRecord(TieBa tieBa) {
        tieBaDao.save(tieBa);
    }

    @Override
    public TieBa getById(Integer bookId) {
        return tieBaDao.queryById(bookId);
    }
}
