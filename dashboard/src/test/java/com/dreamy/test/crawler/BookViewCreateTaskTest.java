package com.dreamy.test.crawler;

import com.dreamy.admin.tasks.BookViewCreateTask;
import com.dreamy.admin.tasks.rank.BookRankCreateTask;
import com.dreamy.domain.ipcool.*;
import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.enums.CrawlerTaskStatusEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.service.iface.ipcool.BookCrawlerInfoService;
import com.dreamy.service.iface.ipcool.BookTagsService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.mongo.BookInfoService;
import com.dreamy.test.BaseJunitTest;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/4/27
 * Time: 上午10:28
 */
@Component
public class BookViewCreateTaskTest  extends BaseJunitTest {
    @Autowired
    BookViewCreateTask bookViewCreateTask;

    @Autowired
    BookRankCreateTask bookRankCreateTask;

    @Test
    public void create(){

        bookRankCreateTask.run();

    }


}
