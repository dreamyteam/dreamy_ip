package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.QueueRoutingKeyEnums;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.mq.QueueService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/3.
 * 关键词搜索
 */
public class KeyWorkTask {

    @Autowired
    IpBookService ipBookService;

    @Resource
    private QueueService queueService;

    public void crawler() throws InterruptedException {


        IpBook ipBook = new IpBook().type(1);
        int currentPage =  1;
        while (true) {
            Page page = new Page();
            page.setPageSize(200);
            page.setCurrentPage(currentPage);
            List<IpBook> list = ipBookService.getIpBookList(ipBook, page);
            for (IpBook book : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("type", book.getType());
                map.put("ipId", book.getId());
                map.put("word", book.getName());
                queueService.push(QueueRoutingKeyEnums.publish_keyword.getKey(),map);
            }
            if (!page.isHasNextPage()) {
                break;
            }
            currentPage++;
        }



    }
}
