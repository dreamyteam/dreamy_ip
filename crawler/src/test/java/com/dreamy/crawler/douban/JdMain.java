package com.dreamy.crawler.douban;

import com.dreamy.handler.jd.JdBean;
import com.dreamy.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;

/**
 * Created by wangyongxing on 16/5/16.
 */
public class JdMain {

    public static void main1(String[] args) {
        String url="http://search.jd.com/Search?keyword=9787219090909&enc=utf-8&pvid=x00em9oi.5otj5i";
        OOSpider ooSpider = OOSpider.create(Site.me(), JdBean.class);
        JdBean jdBean = ooSpider.<JdBean>get(url);

        if (jdBean != null) {
            List<String> list = jdBean.getUrls();
            for(String str:list){
                System.out.println(str);
            }
        }
        ooSpider.close();
    }

    public static void main(String[] args) {
        String url="http://search.jd.com/Search?keyword=9787219090909&enc=utf-8&pvid=x00em9oi.5otj5i";
        String html= HttpUtils.getHtmlGet(url);
        Document document= Jsoup.parse(html);


        Elements images = document.select("div.goods-list-v1>ul.gl-warp>li.gl-item>div.gl-i-wrap>div.p-img>a");
        for(Element element:images){
            System.out.println(element.attr("href"));
        }
        //System.out.println(document.getElementById("J_goodsList").text());
        //System.out.println(html);
    }

}
