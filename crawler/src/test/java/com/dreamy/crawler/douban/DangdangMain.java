package com.dreamy.crawler.douban;

import com.dreamy.handler.amazon.AmazonBean;
import com.dreamy.handler.dangdang.DangdangBean;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class DangdangMain {

    public static void main(String[] args) {
        String url="http://search.dangdang.com/?key=9787219090909&act=input";
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), DangdangBean.class);
        DangdangBean dangdangBean = ooSpider.<DangdangBean>get(url);
        ooSpider.close();
        if (dangdangBean != null) {
            List<String> list = dangdangBean.getUrls();
            for(String str:list){
                System.out.println(str);
            }
        }

    }

}
