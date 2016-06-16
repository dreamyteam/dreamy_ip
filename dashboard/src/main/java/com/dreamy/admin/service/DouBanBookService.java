package com.dreamy.admin.service;

import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/16.
 */
@Service
public class DouBanBookService {


    @Value("${queue_douban_crawler}")
    private String queueName;
    @Autowired
    QueueService queueService;

    private void crawler(int start, String title) {

        String url = "https://book.douban.com/tag/" + title + "?type=T&start=" + start;
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            Elements elements = document.select("ul.subject-list>li.subject-item>div.info>h2>a");
            if (elements != null && elements.size() > 0) {
                int size = elements.size();
                for (int j = 0; j < size; j++) {
                    Element element = elements.get(j);
                    if (element != null) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("title", element.attr("title"));
                        map.put("url", element.attr("href"));
                        queueService.push(queueName, map);
                    }
                }
            }
        }
    }
}
