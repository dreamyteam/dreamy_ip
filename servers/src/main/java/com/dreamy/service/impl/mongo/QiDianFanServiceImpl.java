package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.qidian.QiDianFan;
import com.dreamy.mogodb.dao.QianDainFanDao;
import com.dreamy.service.iface.mongo.QiDianFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangyongxing on 16/6/8.
 */
@Service
public class QiDianFanServiceImpl implements QiDianFanService {

    @Autowired
    QianDainFanDao qianDainFanDao;

    @Override
    public void updateInser(QiDianFan qianDainFan) {
        qianDainFanDao.updateInser(qianDainFan);
    }

    @Override
    public void saveByRecord(QiDianFan qianDainFan) {
        qianDainFanDao.save(qianDainFan);
    }

    @Override
    public QiDianFan getById(Integer bookId)
    {
        return qianDainFanDao.queryById(bookId);
    }
}
