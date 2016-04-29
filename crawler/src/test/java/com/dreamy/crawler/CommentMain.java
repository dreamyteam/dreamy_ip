package com.dreamy.crawler;

import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by wangyongxing on 16/4/27.
 */
public class CommentMain {
    public static void main(String[] args) {
        String url = "https://book.douban.com/subject/1596305/";
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Elements elements = document.getElementsByClass("tlst");
                for (Element element : elements) {
                    Elements ss=element.getElementsByClass("pil");
                    Element element1=ss.first();
                    System.out.println(element1.attr("src"));
                    System.out.println(element1.attr("alt"));
                    Elements element2 =element.select("div.nlst>h3>a");
                    for(Element element3:element2){
                        System.out.println(element3.attr("title"));
                        System.out.println(element3.attr("href"));
                    }

                    Elements element4 =element.select("div.clst>div.review-short>span");
                    for(Element element3:element4){
                        System.out.println(element3.text());
                    }

                    Elements element5 =element.select("div.clst>div.review-short>div>span>span");
                    for(Element element3:element5){
                        System.out.println(element3.text());
                    }


                }
            }
        }
    }
}
