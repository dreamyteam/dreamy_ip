package com.dreamy.service;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/6.
 */
public interface CrawlerService {

    /**
     * 分析一个服务
     *
     * @param url
     *            抓取链接
     * @param source
     *            链接来源
     * @param type
     *            抓取类型
     */
    public Object analye(String url, int source, int type);



}
