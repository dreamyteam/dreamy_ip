package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.KeyWordDao;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.KeyWordConditions;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/29.
 */
@Service
public class KeyWordServiceImpl implements KeyWordService {
    @Resource
    private KeyWordDao keyWordDao;

    @Override
    public void save(KeyWord keyWord) {
        keyWordDao.save(keyWord);
    }

    @Override
    public List<KeyWord> getList(KeyWord keyWord, Page page) {
        Map<String,Object> params= BeanUtils.toQueryMap(keyWord);
        KeyWordConditions conditions=new KeyWordConditions();
        conditions.createCriteria().addByMap(params);
        if(page!=null){
            page.setTotalNum(keyWordDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return keyWordDao.selectByExample(conditions);
    }
}
