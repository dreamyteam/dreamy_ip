package com.dreamy.service.iface.ipcool;


import com.dreamy.domain.ipcool.BookView;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 下午6:31
 */
public interface RankService {

    /**
     *
     * @param bookView
     * @param action
     * @return
     */
    Map<String, String> getCommonParamsByBookIdAndAction(BookView bookView, String action);
}
