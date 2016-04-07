package com.dreamy.service;

import com.dreamy.handler.CrawlerManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ganlv on 11/9/14.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl.class);

	@Autowired
	private CrawlerManage crawlerManage;


	@Override
	public Object analye(String url, int source, int type) {

		return crawlerManage.crawler(source, url, type);
	}





}
