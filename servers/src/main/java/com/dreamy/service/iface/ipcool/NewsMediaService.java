package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.NewsMedia;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/6.
 */
public interface NewsMediaService {

    /**
     *
     * @param newsMedia
     */
    public void save(NewsMedia newsMedia);

    /**
     *
     * @param bookId
     * @return
     */
    public Integer delByBookId(Integer bookId);

    /**
     *
     * @param media
     * @param page
     * @return
     */
    public List<NewsMedia> getList(NewsMedia media, Page page);

    /**
     *
     * @return
     */
    public List<NewsMedia> getDefaultList();
}
