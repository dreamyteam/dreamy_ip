package com.dreamy.service.impl.ipcool;

import com.dreamy.dao.iface.ipcool.BookIndexTaskLogDao;
import com.dreamy.domain.ipcool.BookIndexTaskLog;
import com.dreamy.domain.ipcool.BookIndexTaskLogConditions;
import com.dreamy.enums.BookIndexStatusEnums;
import com.dreamy.enums.BookIndexTypeEnums;
import com.dreamy.service.iface.ipcool.BookIndexTaskLogService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/29
 * Time: 下午12:29
 */
@Service
public class BookIndexTaskLogServiceImpl implements BookIndexTaskLogService {

    @Autowired
    private BookIndexTaskLogDao bookIndexTaskLogDao;

    @Override
    public List<BookIndexTaskLog> getAllByStatus(Integer status) {
        BookIndexTaskLogConditions conditions = new BookIndexTaskLogConditions();
        conditions.createCriteria().andIdGreaterThan(0).andStatusEqualTo(status);

        return bookIndexTaskLogDao.selectByExample(conditions);
    }


    @Override
    public Boolean isTaskActive(Integer indexType) {
        Boolean status = false;
        List<BookIndexTaskLog> bookIndexTaskLogs = getAllByStatus(BookIndexStatusEnums.finished.getStatus());
        if (bookIndexTaskLogs.size() == 5) {
            Map<Integer, BookIndexTaskLog> mapMap = new HashMap<>();
            for (BookIndexTaskLog bookIndexTaskLog : bookIndexTaskLogs) {
                mapMap.put(bookIndexTaskLog.getIndexType(), bookIndexTaskLog);
            }

            BookIndexTaskLog compositeItem = mapMap.get(BookIndexTypeEnums.composite.getType());
            BookIndexTaskLog hotItem = mapMap.get(BookIndexTypeEnums.hot.getType());
            BookIndexTaskLog propagateItem = mapMap.get(BookIndexTypeEnums.propagate.getType());
            BookIndexTaskLog developItem = mapMap.get(BookIndexTypeEnums.develop.getType());
            BookIndexTaskLog reputationItem = mapMap.get(BookIndexTypeEnums.reputation.getType());

            if (indexType.equals(BookIndexTypeEnums.hot.getType())) {
                if (compositeItem.getRunTime() == hotItem.getRunTime()) {
                    status = true;
                }
            } else if (indexType.equals(BookIndexTypeEnums.propagate.getType())) {
                if (compositeItem.getRunTime() == propagateItem.getRunTime()) {
                    status = true;
                }
            } else if (indexType.equals(BookIndexTypeEnums.develop.getType())) {
                if (compositeItem.getRunTime() == developItem.getRunTime()) {
                    if (hotItem.getRunTime() == propagateItem.getRunTime()) {
                        if (hotItem.getRunTime() - developItem.getRunTime() == 1) {
                            status = true;
                        }
                    }
                }
            } else if (indexType.equals(BookIndexTypeEnums.reputation.getType())) {
                if (compositeItem.getRunTime() == reputationItem.getRunTime()) {
                    status = true;
                }
            } else if (indexType.equals(BookIndexTypeEnums.composite.getType())) {
                Date lastUpdatedAt = compositeItem.getUpdatedAt();
                Date currentDate = new Date();

                if (developItem.getRunTime() - 1 == compositeItem.getRunTime()) {
                    long duration = 1000 * 60 * 60;
                    if (TimeUtils.diff(lastUpdatedAt, currentDate) > duration) {
                        status = true;
                    }
                }
            }
        } else if (bookIndexTaskLogs.size() == 0) {
            BookIndexTaskLog bookIndexTaskLog = new BookIndexTaskLog();
            for (BookIndexTypeEnums b : BookIndexTypeEnums.values()) {
                bookIndexTaskLog.indexType(b.getType()).status(BookIndexStatusEnums.finished.getStatus()).runTime(0);
                bookIndexTaskLogDao.save(bookIndexTaskLog);
            }
        }

        return status;
    }

    @Override
    public BookIndexTaskLog getByIndexType(Integer indexType) {
        BookIndexTaskLog bookIndexTaskLog = new BookIndexTaskLog();

        BookIndexTaskLogConditions conditions = new BookIndexTaskLogConditions();
        conditions.createCriteria().andIndexTypeEqualTo(indexType);

        List<BookIndexTaskLog> bookIndexTaskLogs = bookIndexTaskLogDao.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(bookIndexTaskLogs)) {
            bookIndexTaskLog = bookIndexTaskLogs.get(0);
        }

        return bookIndexTaskLog;
    }

    @Override
    public void update(BookIndexTaskLog bookIndexTaskLog) {
        bookIndexTaskLogDao.update(bookIndexTaskLog);
    }
}
