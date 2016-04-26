package com.dreamy.dao.iface.ipcool;

import com.dreamy.dao.BaseDao;
import com.dreamy.domain.ipcool.BookRankHistory;
import com.dreamy.domain.ipcool.BookRankHistoryConditions;

/**
 * Created by wangyongxing on 16/4/26.
 * 指数排名历史记录
 */
public interface BookRankHistoryDao extends BaseDao<BookRankHistory,Integer,BookRankHistoryConditions> {
}
