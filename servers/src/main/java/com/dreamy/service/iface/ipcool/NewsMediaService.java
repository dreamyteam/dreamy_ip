package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.NewsMedia;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/6.
 */
public interface NewsMediaService {

    public void save(NewsMedia newsMedia);

    public Integer delByBookId(Integer bookId);

    public List<NewsMedia> getList(NewsMedia media, Page page);
}
