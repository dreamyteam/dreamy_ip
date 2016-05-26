package com.dreamy.crawler.handler;

import com.dreamy.service.iface.mongo.UserAgentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractCrawlerHandler implements CrawlerHandler {
    @Autowired
    protected UserAgentService userAgentService;

	@Autowired
	private CrawlerManage crawlerManage;

	@PostConstruct
	public void init() {
		crawlerManage.register(this);
	}

	/**
	 * 处理类的Id
	 * @return
	 */
	public abstract Integer getId();
}
