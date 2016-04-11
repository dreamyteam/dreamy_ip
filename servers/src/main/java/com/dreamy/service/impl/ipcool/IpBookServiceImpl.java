package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.IpBookDao;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.domain.ipcool.IpBookConditions;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/11.
 */
@Service
public class IpBookServiceImpl implements IpBookService {
    @Resource
    private IpBookDao ipBookDao;
    @Override
    public IpBook save(IpBook ipBook) {
        ipBookDao.save(ipBook);
        return ipBook;
    }

    @Override
    public IpBook getById(Integer id) {
        return ipBookDao.selectById(id);
    }

    @Override
    public List<IpBook> getIpBookList(IpBook ipBook, Page page)
    {
        Map<String,Object> params= BeanUtils.toQueryMap(ipBook);
        IpBookConditions conditions=new IpBookConditions();

        conditions.createCriteria().addByMap(params);
        if(page!=null){
            int row=ipBookDao.countByExample(conditions);
            page.setTotalNum(row);
            conditions.setPage(page);

        }
        List<IpBook> list=ipBookDao.selectByExample(conditions);
        return list;
    }

    @Override
    public int update(IpBook ipBook) {
        return ipBookDao.update(ipBook);
    }
}
