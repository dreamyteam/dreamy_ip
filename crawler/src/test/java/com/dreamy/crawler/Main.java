package com.dreamy.crawler;

import com.dreamy.handler.sougou.NewsSougou;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/20.
 */
public class Main {
    public  static void test(String url)
    {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), NewsSougou.class);
        NewsSougou sougou = ooSpider.<NewsSougou>get(url);
        if(sougou!=null) {
            System.out.println(sougou.getNum());
        }
    }

    public static void main(String[] args) {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), NewsSougou.class);
        String url="http://news.sogou.com/news?query=斗破苍穹";

        NewsSougou baike = ooSpider.<NewsSougou>get(url);
        System.out.println(baike.getNum());
        List<String> list=baike.getUrls();
        int size=list.size();
        for(int i=0;i<size;i++)
        {
            test(list.get(i));
        }

        ooSpider.close();
    }

}
