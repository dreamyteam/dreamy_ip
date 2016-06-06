package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookCrawlerInfo;
import com.dreamy.domain.ipcool.BookIndexHistory;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookIndexHistoryService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.NumberUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wangyongxing on 16/5/10.
 */
@Component
public class BookIndexHistoryTask {


    @Resource
    private BookViewService bookViewService;
    @Resource
    BookIndexHistoryService bookIndexHistoryService;


    public void copy() {
        BookView entity = new BookView().type(1);
        int currentPage = 1;
        bookIndexHistoryService.delByDate(new Date());
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(currentPage);
            List<BookView> list = bookViewService.getList(entity, page);
            for(BookView info:list){
                BookIndexHistory history=new BookIndexHistory();
                history.setHotIndex(info.getHotIndex());
                history.setActivityIndex(info.getActivityIndex()>0?info.getActivityIndex()+ NumberUtils.randomInt(-50,100):0);
                history.setCompositeIndex(info.getCompositeIndex()>0?info.getCompositeIndex()+ NumberUtils.randomInt(-50,100):0);
                history.setPropagateIndex(info.getPropagateIndex()>0?info.getPropagateIndex()+ NumberUtils.randomInt(-50,100):0);
                history.setDevelopIndex(info.getDevelopIndex()>0?info.getDevelopIndex()+ NumberUtils.randomInt(-50,100):0);
                history.setBookId(info.getBookId());
                history.setStatus(1);
                bookIndexHistoryService.save(history);
            }
            if (!page.isHasNextPage()) {
                break;
            }
            currentPage++;

        }
    }
}
