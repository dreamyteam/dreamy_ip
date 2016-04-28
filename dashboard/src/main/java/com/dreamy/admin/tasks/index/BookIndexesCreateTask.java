package com.dreamy.admin.tasks.index;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/28
 * Time: 下午2:17
 */
@Component
public class BookIndexesCreateTask {

    @Scheduled(fixedDelay = 8000)
    private void run() {

    }


}
