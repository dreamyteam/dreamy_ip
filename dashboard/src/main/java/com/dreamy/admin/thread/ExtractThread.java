package com.dreamy.admin.thread;

import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.mogodb.beans.BookInfo;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/17.
 */
public class ExtractThread implements Runnable {

    private ExtractBookViewService extractBookViewService;

    private List<IpBook> list;

    public ExtractThread(ExtractBookViewService extractBookViewService, List<IpBook> list) {
        super();
        this.extractBookViewService = extractBookViewService;
        this.list = list;

    }

    @Override
    public void run() {
        try {
            extractBookViewService.extract(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
