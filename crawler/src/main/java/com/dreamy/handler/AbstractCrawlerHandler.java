package com.dreamy.handler;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractCrawlerHandler implements CrawlerHandler {

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
	public abstract int getId();
}
