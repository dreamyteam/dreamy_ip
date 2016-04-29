package com.dreamy.dao.impl.ipcool;

import com.dreamy.dao.BaseDaoImpl;
import com.dreamy.dao.iface.ipcool.KeyWordDao;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.KeyWordConditions;
import com.dreamy.mapper.ipcool.KeyWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/4/29.
 */
@Repository("keyWordDao")
public class KeyWordDaoImpl extends BaseDaoImpl<KeyWord, Integer, KeyWordConditions> implements KeyWordDao {

    @Resource
    private KeyWordMapper keyWordMapper;

    @Override
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(keyWordMapper);

    }
}
