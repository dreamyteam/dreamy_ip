package com.dreamy.admin.tasks.rank;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.enums.BookRankEnums;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/5
 * Time: 下午6:48
 * <p/>
 * 小说综合排名
 */
@Component
public class CompasiteRankTask {

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private RedisClientService redisClientService;

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void run() {
        Page page = new Page();
        page.setPageSize(300);
        List<BookView> bookViewList = bookViewService.getListByPageAndOrder(page, "id asc");
        if (CollectionUtils.isEmpty(bookViewList)) {
            return;
        }

        Integer totalPage = page.getTotalPage();
        Integer currentPage = page.getCurrentPage();

        do {
            for (BookView bookView : bookViewList) {
                redisClientService.zadd(BookRankEnums.composite.getCacheKey(), bookView.getCompositeIndex(), bookView.getBookId().toString());
            }
        } while (totalPage != currentPage);


    }
}
