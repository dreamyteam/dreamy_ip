package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.KeyWord;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/29.
 */
public interface KeyWordService {

    /**
     *
     * @param keyWord
     */
    void save(KeyWord keyWord);

    /**
     *
     * @param keyWord
     * @param page
     * @return
     */
    List<KeyWord> getList(KeyWord keyWord,Page page);

    /**
     *
     * @param keyWord
     */
    public void saveOrUpdate(KeyWord keyWord);

    public List<KeyWord> getByBookId(Integer bookId);


}
