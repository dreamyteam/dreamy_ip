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

	@Override
	public Object analye(String url, int type) {

		switch (type) {
		case CRAWLER_NET_BOOK:
			return getByUrl(url);
		case CRAWLER_PUBLISHE_BOOK:
			return getByUrl(url);
		default:
			return null;
		}
	}
}
