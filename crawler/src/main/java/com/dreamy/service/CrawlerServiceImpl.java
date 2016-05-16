package com.dreamy.service;

import com.dreamy.service.mq.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ganlv on 11/9/14.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl.class);
    @Autowired
    private QueueService queueService;
    @Value("${crawler_book_isbn}")
    private String queueName;

    @Override
    public void push(String isbn) {
        Map<String,String> map=new HashMap<String, String>();
        map.put("isbn",isbn);
        queueService.push(queueName, map);
    }
}
