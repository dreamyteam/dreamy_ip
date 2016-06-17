package com.dreamy.test.ipcool;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.IpBook;
import com.dreamy.enums.IpTypeEnums;
import com.dreamy.enums.OperationEnums;
import com.dreamy.service.iface.ipcool.IpBookService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.test.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/17.
 */
public class KeyWordTest extends BaseJunitTest {

    @Autowired
    private IpBookService ipBookService;

    @Autowired
    QueueService queueService;
    @Value("${queue_keyword_wx}")
    private String queueName1;
    @Value("${queue_keyword_wb}")
    private String queueName2;
    @Value("${queue_keyword_baidu_sougou}")
    private String queueName3;

    @Test
    public void test() {
        IpBook entity = new IpBook();
        entity.setType(IpTypeEnums.chuban.getType());
        Page page = new Page();
        page.setPageSize(200);
        int current = 1;
        while (true) {
            page.setCurrentPage(current);
            List<IpBook> list = ipBookService.getIpBookList(entity, page);

            for (IpBook info : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("bookId", info.getId());
                map.put("key", "testkey");
                map.put("operation", OperationEnums.update.getCode());
                map.put("search_keyword", info.getSearchKeyword());
                map.put("type", 1);
                queueService.push(queueName1, map);
                queueService.push(queueName2, map);
                queueService.push(queueName3, map);
            }
            if (!page.isHasNextPage()) {
                break;
            }
            current++;
        }
    }
}
