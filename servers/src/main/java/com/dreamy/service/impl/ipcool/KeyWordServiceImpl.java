package com.dreamy.service.impl.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.dao.iface.ipcool.KeyWordDao;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.domain.ipcool.KeyWordConditions;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.utils.BeanUtils;
import com.dreamy.utils.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
        Map<String, Object> params = BeanUtils.toQueryMap(keyWord);
        KeyWordConditions conditions = new KeyWordConditions();
        conditions.createCriteria().addByMap(params);
        if (page != null) {
            page.setTotalNum(keyWordDao.countByExample(conditions));
            conditions.setPage(page);
        }

        return keyWordDao.selectByExample(conditions);
    }

    @Override
    public void saveOrUpdate(KeyWord keyWord) {


        KeyWordConditions conditions = new KeyWordConditions();
        conditions.createCriteria().andBookIdEqualTo(keyWord.getBookId()).andSourceEqualTo(keyWord.getSource());
        List<KeyWord> list = keyWordDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(list)) {
            KeyWord old = list.get(0);
            keyWord.setId(old.getId());
            keyWordDao.update(keyWord);
        } else {
            keyWordDao.save(keyWord);
        }


    }

    @Override
    public List<KeyWord> getByBookId(Integer bookId) {
        KeyWordConditions conditions = new KeyWordConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId);
        return keyWordDao.selectByExample(conditions);
    }

    @Override
    public KeyWord getByBookIdAndSource(Integer bookId, Integer source) {

        KeyWordConditions conditions = new KeyWordConditions();
        conditions.createCriteria().andBookIdEqualTo(bookId).andSourceEqualTo(source);
        List<KeyWord> keyWordList = keyWordDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(keyWordList)) {
            return keyWordList.get(0);
        }

        return null;
    }

    @Override
    public Map<Integer, Double> getKeyWordSourceMap() {
        Map<Integer, Double> map = new HashMap<>();
        for (KeyWordEnums enums : KeyWordEnums.values()) {
            map.put(enums.getType(), enums.getPercent());
        }
        return map;
    }
}
