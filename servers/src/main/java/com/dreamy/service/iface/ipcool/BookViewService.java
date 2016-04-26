package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/26.
 */
public interface BookViewService {
    /**
     * 保存
     * @param bookView
     */
    public void save(BookView bookView);

    /**
     * 更新
     * @param bookView
     * @return
     */
    public Integer update(BookView bookView);

    public List<BookView> getList(BookView bookView, Page page);

    public BookView getById(Integer id);
}
