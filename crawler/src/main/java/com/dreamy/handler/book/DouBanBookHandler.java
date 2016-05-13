package com.dreamy.handler.book;

import com.dreamy.service.cache.CommonService;
import com.dreamy.service.mq.QueueService;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${queue_crawler_douban_book}")
    private String queueName;

    public void crawler() throws Exception {


        int pageSize = 20;
        String value = "";
        for (int i = 4; i < 50; i++) {
            int start = pageSize * i;
            value = test(start, value);


        }

    }

    private String test(int start, String value) throws Exception {
        String cc = "";
        if (StringUtils.isEmpty(value)) {
            cc = "ips" + NumberUtils.randomInt(1, 90);
            value = (String) commonService.getCacheService().get(cc);
        }
        if (StringUtils.isNotEmpty(value)) {
            String arr[] = value.split(":");
            String hostname = arr[0];
            int port = Integer.valueOf(arr[1]);
            String url = "https://book.douban.com/tag/文学?type=T&start=" + start;
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

            }
            else {
                commonService.getCacheService().remove(cc);
                test(start, "");
            }
        }
        else{
            test(start, "");
        }


        return value;
    }
}
