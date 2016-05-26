package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.HotWord;
import com.dreamy.mogodb.dao.HotWordDao;
import com.dreamy.service.iface.mongo.HotWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 下午12:07
 */
@Service
public class HotWordServiceImpl implements HotWordService {

    @Autowired
    private HotWordDao hotWordDao;

    @Override
    public HotWord getById(Integer id) {
        return hotWordDao.queryById(id);
    }
}
