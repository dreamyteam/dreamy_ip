package com.dreamy.service.impl.mongo;

import com.dreamy.mogodb.beans.NetBookTicket;
import com.dreamy.mogodb.dao.NetBookTicketDao;
import com.dreamy.service.iface.mongo.NetBookTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangyongxing on 16/6/7.
 */
@Service
public class NetBookTicketServiceImpl implements NetBookTicketService {

    @Autowired
    NetBookTicketDao netBookTicketDao;

    @Override
    public void updateInser(NetBookTicket netBookTicket) {
        netBookTicketDao.updateInser(netBookTicket);
    }

    @Override
    public void saveByRecord(NetBookTicket netBookTicket) {
        netBookTicketDao.save(netBookTicket);
    }

    @Override
    public NetBookTicket getById(Integer code) {
        return netBookTicketDao.queryById(code);
    }
}
