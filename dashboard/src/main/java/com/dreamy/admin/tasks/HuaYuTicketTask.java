package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.service.mq.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class HuaYuTicketTask {


    @Value("${queue_crawler_huayu_ticket}")
    private String queueName;

    @Autowired
    private QueueService queueService;

    @Scheduled(cron = "0 10 16 * * ?")
    public void run() {
        Map<String, Integer> map = new HashMap<>();
        Page page=new Page();
        page.setPageSize(10);
        page.setTotalNum(156);
        int current=1;
        while (true) {
            page.setCurrentPage(current);
            map.put("start",page.getStartIndex());
            map.put("end",page.getStartIndex()+page.getEndIndex());
            queueService.push(queueName, map);
        }
    }

    public static void main(String[] args) {
        Page page=new Page();
        page.setPageSize(10);
        int current=1;
        while (true) {
            page.setCurrentPage(current);
            page.setTotalNum(156);
            System.out.println(page.getStartIndex()+"-");
            if(!page.isHasNextPage()){
                break;
            }
            current++;
        }
    }
}
