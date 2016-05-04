package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.KeyWord;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/29.
 */
public interface KeyWordService {

    void save(KeyWord keyWord);

    List<KeyWord> getList(KeyWord keyWord,Page page);

    public void saveOrUpdate(KeyWord keyWord);


}
