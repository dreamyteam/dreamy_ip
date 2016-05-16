package com.dreamy.handler.book;

import com.dreamy.service.cache.CommonService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/12.
 */
@Component
public class DouBanBookHandler {

    @Autowired
    QueueService queueService;
    @Autowired
    CommonService commonService;


    @Autowired
    private ListOperations<String, String> listOperations;

    @Value("${queue_crawler_douban_book}")
    private String queueName;

    public void crawler(String title){
        int pageSize = 20;
        String value = "";
        boolean check = false;
        for (int i = 0; i < 50; i++) {
            int start = pageSize * i;
            while (true) {
                if (StringUtils.isEmpty(value)) {
                    value = listOperations.leftPop("proxy_ips_list");
                    if (StringUtils.isEmpty(value)){
                        break;
                    }
                }
                check = crawlering(start, value,title);
                if (check) {
                    break;
                }
                value = "";

            }


        }

    }

    private boolean crawlering(int start, String value,String title) {
        if (StringUtils.isNotEmpty(value)) {
            String arr[] = value.split(":");
            String hostname = arr[0];
            int port = Integer.valueOf(arr[1]);
            String url = "https://book.douban.com/tag/"+title+"?type=T&start=" + start;
            OOSpider ooSpider = OOSpider.create(Site.me().setHttpProxy(new HttpHost(hostname, port)), DouBan.class);
            DouBan douBan = ooSpider.<DouBan>get(url);
            ooSpider.close();
            if (douBan != null) {
                List<String> list = douBan.getUrls();
                int size = list.size();
                for (int j = 0; j < size; j++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", douBan.getNames().get(j));
                    map.put("url", douBan.getUrls().get(j));
                    queueService.push(queueName, map);
                }
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

}
