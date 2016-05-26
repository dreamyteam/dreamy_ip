package com.dreamy.crawler.douban;

import com.dreamy.handler.amazon.AmazonBean;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class AmazonMain {

    public static void main(String[] args) {
        String url="https://www.amazon.cn/s/ref=nb_sb_noss?__mk_zh_CN=亚马逊网站&url=search-alias%3Dstripbooks&field-keywords=9787219090909";
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), AmazonBean.class);
        AmazonBean amazonBean = ooSpider.<AmazonBean>get(url);
        ooSpider.close();
        if (amazonBean != null) {
            List<String> list = amazonBean.getUrls();
            for(String str:list){
                System.out.println(str);
            }
        }

    }

}
