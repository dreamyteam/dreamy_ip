package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/6.
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class ConstUtil {

    public static final int CRAWLER_TYPE_PROGRAM = 1;
    public static final int CRAWLER_TYPE_ALBUM = 2;
    public static final int CRAWLER_TYPE_SUBSCRIBE = 3;

    public static final int CRAWLER_STATUS_INIT = 1;
    public static final int CRAWLER_STATUS_CRAWL_START = 2;
    public static final int CRAWLER_STATUS_SHUOSHU_ANALYZE = 3;
    public static final int CRAWLER_STATUS_VIDEO_DOWNLOAD = 4;
    public static final int CRAWLER_STATUS_FILE_TRANSCODING = 5;
    public static final int CRAWLER_STATUS_FILE_MERGE = 6;
    public static final int CRAWLER_STATUS_MP3_URL = 9;
    public static final int CRAWLER_STATUS_MP3_DOWNLOAD = 10;
    public static final int CRAWLER_STATUS_FILE_UPLOAD = 13;
    public static final int CRAWLER_STATUS_FILE_UPLOAD_FINISH = 14;
    public static final int CRAWLER_STATUS_FINISH = 15;
    public static final int CRAWLER_STATUS_SHUOSHU_ANALYZE_FAILURE = 23;
    public static final int CRAWLER_STATUS_VIDEO_DOWNLOAD_FAILURE = 24;
    public static final int CRAWLER_STATUS_FILE_TRANSCODING_FAILURE = 25;
    public static final int CRAWLER_STATUS_FILE_MERGE_FAILURE = 26;
    public static final int CRAWLER_STATUS_MP3_URL_FAILURE = 29;
    public static final int CRAWLER_STATUS_MP3_DOWNLOAD_FAILURE = 30;
    public static final int CRAWLER_STATUS_FILE_UPLOAD_FAILURE = 33;
    public static final int CRAWLER_STATUS_FILE_UPLOAD_EXPORT_FAILURE = 34;

    public static final int CRAWLER_SOURCE_AMAZON = 1;
    public static final int CRAWLER_SOURCE_JD = 2;
    public static final int CRAWLER_SOURCE_DD = 3;
    public static final int CRAWLER_SOURCE_XIMA = 4;
    public static final int CRAWLER_SOURCE_LIZHI = 5;
    public static final int CRAWLER_SOURCE_IFENG = 6;

    private static final Map<String, String> CRAWL_SOURCES = new LinkedHashMap<String, String>();
    static {
        CRAWL_SOURCES.put("1", "优酷");
        CRAWL_SOURCES.put("2", "56网");
        CRAWL_SOURCES.put("3", "乐视");
        CRAWL_SOURCES.put("4", "喜马拉雅");
        CRAWL_SOURCES.put("5", "荔枝");
        CRAWL_SOURCES.put("6", "凤凰");
    }

    public static Map<String, String> getCrawlSourcesMap() {
        return CRAWL_SOURCES;
    }

    private static final Map<String, String> CRAWL_TYPE = new LinkedHashMap<String, String>();
    static {
        CRAWL_TYPE.put("1", "单节目抓取");
        CRAWL_TYPE.put("2", "专辑抓取");
        CRAWL_TYPE.put("3", "订阅抓取");
    }

    public static Map<String, String> getCrawlTypeMap() {
        return CRAWL_TYPE;
    }

    private static final Map<String, String> CRAWL_STATUS = new LinkedHashMap<String, String>();
    static {
        CRAWL_STATUS.put("1", "等待抓取");
        CRAWL_STATUS.put("2", "开始抓取");
        CRAWL_STATUS.put("3", "硕鼠解析");
        CRAWL_STATUS.put("4", "视频文件下载");
        CRAWL_STATUS.put("5", "文件转码");
        CRAWL_STATUS.put("6", "文件合并");
        CRAWL_STATUS.put("9", "mp3下载链接处理");
        CRAWL_STATUS.put("10", "mp3文件下载");
        CRAWL_STATUS.put("13", "文件开始上传");
        CRAWL_STATUS.put("14", "文件完成上传");
        CRAWL_STATUS.put("15", "任务执行完成");
        CRAWL_STATUS.put("23", "硕鼠解析失败");
        CRAWL_STATUS.put("24", "视频下载失败");
        CRAWL_STATUS.put("25", "视频转码失败");
        CRAWL_STATUS.put("26", "文件合并失败");
        CRAWL_STATUS.put("29", "mp3链接获取失败");
        CRAWL_STATUS.put("30", "mp3文件下载失败");
        CRAWL_STATUS.put("33", "文件上传失败");
        CRAWL_STATUS.put("34", "导入后台失败");
        CRAWL_STATUS.put("41", "订阅占位");
    }

    public static Map<String, String> getCrawlStatusMap() {
        return CRAWL_STATUS;
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

