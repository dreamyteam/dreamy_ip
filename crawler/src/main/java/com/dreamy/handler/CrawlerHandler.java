package com.dreamy.handler;

import com.dreamy.mogodb.beans.Book;
import com.dreamy.mogodb.beans.BookInfo;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface CrawlerHandler {


    public static final int CRAWLER_NET_BOOK = 1;
    public static final int CRAWLER_PUBLISHE_BOOK = 2;


    public int getId();

    /**
     * 解析链接对应网页
     *
     * @param url
     *            目标链接地址
     * @param type
     *            解析类型（专辑，单节目，热门）
     * @return
     */
    public Object analye(String url, int type);

    /**
     * 解析链接获得专辑信息
     *
     * @param url
     *            目标链接地址
     * @return
     */
    public BookInfo getByUrl(String url);


    /**
     * 解析下载链接
     *
     * @param url
     * @return
     */
    public String analyeUrl(String url);
}
