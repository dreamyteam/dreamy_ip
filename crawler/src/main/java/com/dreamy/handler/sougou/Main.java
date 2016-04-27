package com.dreamy.handler.sougou;

import com.dreamy.utils.StringUtils;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/20.
 */
public class Main {
    public  static void test(String url)
    {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), Sougou.class);
        Sougou sougou = ooSpider.<Sougou>get(url);
        if(sougou!=null) {
            System.out.println(sougou.getNum());
        }
    }

    public static void main(String[] args) {
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), Sougou.class);
        String url="http://news.sogou.com/news?query=斗破苍穹";

        Sougou baike = ooSpider.<Sougou>get(url);
        List<String> list=baike.getUrls();
        for(String str:list)
        {
          if(StringUtils.isNotEmpty(str))
          {
           test(str);
          }
        }
        ooSpider.close();
    }

}
