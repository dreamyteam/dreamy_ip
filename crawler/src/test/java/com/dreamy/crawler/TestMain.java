package com.dreamy.crawler;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/4/6.
 */
public class TestMain {
    public static void main11(String[] args) {
        String url="http://www.qidian.com/Book/3620214.aspx";
        String html = HttpUtils.getHtmlGet(url, "http://www.qidian.com/Book/3620214.aspx");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Elements images = document.select("div.pic_box >a>img");
                if (images != null && images.size() > 0) {

                        Element image = images.first();
                        if (image != null) {
                            System.out.println(image.attr("src"));
                        }
                    }

                }
            Elements images = document.select("div.book_info>div.title>h1");
            System.out.println(images.first().text());
            Elements images1 = document.select("div.book_info>div.title>span").first().getElementsByAttributeValue("itemprop","name");
            Elements images11 = document.getElementById("divBookInfo").getElementsByAttributeValue("itemprop","name");

            System.out.println(11);



            }

    }

    public static void main(String[] args) {
        String url="http://www.amazon.cn/gp/product/B00VWVAFAG/ref=s9_acsd_ri_bw_rw_r0_p8_i?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=merchandised-search-5&pf_rd_r=0KFAPX9E42KMYPW0V164&pf_rd_t=101&pf_rd_p=261616452&pf_rd_i=658390051";
        String html = HttpUtils.getHtmlGet(url, "http://www.qidian.com/Book/3620214.aspx");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Elements images = document.select("div.content >ul>li");
                if (images != null && images.size() > 0) {

                    Element image = images.first();
                    if (image != null) {
                        String content=image.text();
                        if(StringUtils.isNotEmpty(content))
                        {
                            String arr[]=content.split(";");

                            bean.setPress(arr[0].replace("出版社:",""));
                            bean.setPushTime(result(arr[1]));
                        }
                    }
                }
                Elements authos = document.getElementsByClass("author");

                if (authos != null && authos.size() > 0) {
                    String author="";
                    for(Element element:authos)
                    {
                        author=author+element.text();
                    }
                    System.out.println(author);
                }
                Element sort = document.getElementById("SalesRank");
                System.out.println(sort.text());

            }





        }
    }

    public static String  result(String content) {
        String date="";
        Pattern p = Pattern
                .compile("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}");
        Matcher m = p.matcher(content);
        while (m.find()) {
            if (!"".equals(m.group())) {
                 date = m.group();
                date = date.replaceAll("年", "-");
                date = date.replaceAll("月", "-");
                date = date.replaceAll("/", "-");
                System.out.println(date);
            }
        }
        return date;
    }
}
