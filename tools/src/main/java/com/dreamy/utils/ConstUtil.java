package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/6.
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class ConstUtil {


    public static final Integer CRAWLER_SOURCE_AMAZON = 1;
    public static final Integer CRAWLER_SOURCE_JD = 2;
    public static final Integer CRAWLER_SOURCE_DD = 3;
    public static final Integer CRAWLER_SOURCE_DB = 4;


    private static final Map<Integer, String> CRAWL_SOURCES = new LinkedHashMap<Integer, String>();
    static {
        CRAWL_SOURCES.put(1, "亚马逊");
        CRAWL_SOURCES.put(2, "京东");
        CRAWL_SOURCES.put(3, "当当书城");
        CRAWL_SOURCES.put(4, "豆瓣");

    }

    public static Map<Integer, String> getCrawlSourcesMap() {
        return CRAWL_SOURCES;
    }



    private static final Map<String, String> CRAWL_TASK_STATUS = new LinkedHashMap<String, String>();
    static {
        CRAWL_TASK_STATUS.put("1", "等待抓取");
        CRAWL_TASK_STATUS.put("2", "开始抓取");
        CRAWL_TASK_STATUS.put("15", "任务执行完成");
    }

    public static Map<String, String> getCrawlTaskStatusMap() {
        return CRAWL_TASK_STATUS;
    }

    private static final Map<String, String> CRAWL_SUBSCRIBE_TYPE = new LinkedHashMap<String, String>();
    static {
        CRAWL_SUBSCRIBE_TYPE.put("true", "是");
        CRAWL_SUBSCRIBE_TYPE.put("false", "否");
    }

    public static Map<String, String> getCrawlSubscribeMap() {
        return CRAWL_SUBSCRIBE_TYPE;
    }

    public final class Page {
        public static final String DEFAULT_ERROR_PAGE = "error_message";
    }
}

