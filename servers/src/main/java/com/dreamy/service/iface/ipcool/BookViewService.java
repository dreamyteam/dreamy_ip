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
    public List<BookView> getList(BookView bookView, Page page);

    /**
     * @param id
     * @return
     */
    public BookView getById(Integer id);


    /**
     *
     * @param page
     * @param order
     * @return
     */
    public List<BookView> getListByPageAndOrder(Page page,String order);

    /**
     *
     * @param bookId
     * @return
     */
    public BookView getByBookId(Integer bookId);



}
