package com.crawler.test;

import com.dreamy.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by wangyongxing on 16/5/30.
 */
public class NewsSougouMain {
    public static void main(String[] args) {

        String word="奥迪a4换代";
        word = HttpUtils.encodeUrl(word);
        String url = "http://news.sogou.com/news?query=" + word;
        String html= HttpUtils.getHtmlGet(url,"GBK");
        Document document= Jsoup.parse(html);
        System.out.println(html);
        if(document!=null){
            Elements elements = document.getElementsByClass("filt-pop");
            if(elements!=null&&elements.size()>1){
                Elements hrefs=elements.get(1).getElementsByTag("a");
               for(Element elements1:hrefs)
               {
                   System.out.println(elements1.attr("href"));
               }

            }
        }
    }
}
