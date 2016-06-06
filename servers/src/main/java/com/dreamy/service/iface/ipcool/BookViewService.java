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
     *
     * @param bookView
     */
    public void save(BookView bookView);

    /**
     * 更新
     *
     * @param bookView
     * @return
     */
    public Integer update(BookView bookView);

    /**
     * @param bookView
     * @param page
     * @return
     */
    public List<BookView> getList(BookView bookView, Page page,String order);

    /**
     * 获取当前表的长度
     *
     * @return
     */
    Integer getTotalCountByType(Integer type);

    /**
     * @param id
     * @return
     */
    public BookView getById(Integer id);

    /**
     * @param ids
     * @return
     */
    public List<BookView> getListByBookIds(List<Integer> ids);

    /**
     * @param page
     * @param order
     * @return
     */
    public List<BookView> getListByPageAndOrderAndType(Page page, String order,Integer type);

    /**
     * @param bookId
     * @return
     */
    public BookView getByBookId(Integer bookId);


}
