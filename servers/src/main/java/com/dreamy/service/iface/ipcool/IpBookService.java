package com.dreamy.service.iface.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/11.
 */
public interface IpBookService {

    IpBook save(IpBook ipBook);

    IpBook getById(Integer id);

    List<IpBook> getIpBookList(IpBook ipBook, Page page);

    int update(IpBook ipBook);
}
