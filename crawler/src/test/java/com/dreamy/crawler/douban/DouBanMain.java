package com.dreamy.crawler.douban;

import com.dreamy.handler.book.DouBan;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/12.
 */
public class DouBanMain {
    public static void main(String[] args) {

        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), DouBan.class);
        int pageSize=20;
        for (int i = 0; i < 50; i++) {
            int start=pageSize*i;
            String url = "https://book.douban.com/tag/文学?type=T&start="+start;
            DouBan douBan = ooSpider.<DouBan>get(url);
            ooSpider.close();
            if (douBan != null) {
                List<String> list = douBan.getUrls();
                int size=list.size();
                try {
                    for (int j = 0; j < size; j++) {
                        System.out.println(douBan.getNames().get(j) + "----" + douBan.getUrls().get(j));
                    }
                }catch (Exception e){
                    System.out.println(i);
                }

            }
        }

    }
}
