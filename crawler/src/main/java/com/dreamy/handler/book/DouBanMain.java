package com.dreamy.handler.book;

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
            String url = "https://book.douban.com/tag/小说?type=T&start="+start;
            DouBan douBan = ooSpider.<DouBan>get(url);
            ooSpider.close();
            if (douBan != null) {
                List<String> list = douBan.getUrls();
                System.out.println(11);

            }
        }

    }
}
