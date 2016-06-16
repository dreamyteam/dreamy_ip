package com.dreamy.admin.tasks;

import com.dreamy.service.mq.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/13.
 */
@Component
public class HuaYuTask {


    @Value("${queue_crawler_huayu_ticket}")
    private String queueName;
    @Autowired
    private QueueService queueService;


    /***
     * 花语月票 任务
     */
    @Scheduled(cron = "0 10 03 * * ?")
    public void run() {
        for (int i = 1; i < 156; i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put("page", i);
            queueService.push(queueName, map);
        }
    }


}
