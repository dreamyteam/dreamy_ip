package com.dreamy.service.iface.ipcool;


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
     * @param bookId
     */
    Map<String, String> getCommonParamsByBookIdAndAction(Integer bookId,String action);
}
