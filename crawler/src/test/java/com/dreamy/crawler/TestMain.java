package com.dreamy.crawler;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
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
        String url = "http://www.qidian.com/Book/3620214.aspx";
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
            Elements images1 = document.select("div.book_info>div.title>span").first().getElementsByAttributeValue("itemprop", "name");
            Elements images11 = document.getElementById("divBookInfo").getElementsByAttributeValue("itemprop", "name");

            System.out.println(11);


        }

    }

    public static void main111(String[] args) {
        String url = "http://www.amazon.cn/gp/product/B00VWVAFAG/ref=s9_acsd_ri_bw_rw_r0_p8_i?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=merchandised-search-5&pf_rd_r=0KFAPX9E42KMYPW0V164&pf_rd_t=101&pf_rd_p=261616452&pf_rd_i=658390051";
        String html = HttpUtils.getHtmlGet(url, "http://www.qidian.com/Book/3620214.aspx");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Elements images = document.select("div.content >ul>li");
                if (images != null && images.size() > 0) {

                    Element image = images.first();
                    if (image != null) {
                        String content = image.text();
                        if (StringUtils.isNotEmpty(content)) {
                            String arr[] = content.split(";");

                            bean.setPress(arr[0].replace("出版社:", ""));
                            bean.setPushTime(result(arr[1]));
                        }
                    }
                }
                Elements authos = document.getElementsByClass("author");

                if (authos != null && authos.size() > 0) {
                    String author = "";
                    for (Element element : authos) {
                        author = author + element.text();
                    }
                    System.out.println(author);
                }

                Element sort = document.getElementById("SalesRank");


                System.out.println(sort.childNode(2).toString().replace("(", ""));
                bean.setSaleSort(sort.text());
                String arr[] = sort.text().split("- ");

                Element sss = document.getElementById("summaryStars").getElementsByTag("a").first();

                System.out.println(sss.childNode(2));
                System.out.println(document.getElementsByTag("noscript").text() + "\n");
                Element sss1 = document.getElementById("s_content_0");
                System.out.println(sss1.text() + "\n");
                Element sss2 = document.getElementById("s_content_1");
                System.out.println(sss2.text() + "\n");

                Element score = document.getElementById("avgRating");
                System.out.println(score.text());

                Elements types = document.select("ul.zg_hrsr>li");
                StringBuffer typeInfo = new StringBuffer();
                if (types != null && types.size() > 0) {

                    for (Element element : types) {
                        typeInfo.append(element.text() + ",");

                    }

                    System.out.println(typeInfo.toString());
                }


            }


        }
    }

    public static String result(String content) {
        String date = "";
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


    public static void main222(String[] args) {
        String url = "http://item.jd.com/11678007.html";
        String html = HttpUtils.getHtmlGetBycharSet(url, "gbk");
        System.out.println(html);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Element sort = document.getElementById("p-author");
                System.out.println(sort.text());
                Element image = document.getElementById("spec-n1").getElementsByTag("img").first();
                System.out.println(image.attr("src"));
                System.out.println(image.attr("alt"));

                Element sort212 = document.getElementById("summary");
                Elements types = document.select("ul.p-parameter-list>li");
                if (types != null && types.size() > 0) {

                    System.out.println(types.get(0).attr("title"));
                    System.out.println(types.get(7).attr("title"));


                }
                Element sort21211 = document.getElementsByClass("book-detail-content").first();
                System.out.println(sort21211.text());


            }
        }

    }


    public static void mainww(String[] args) {
        String url = "http://product.dangdang.com/23274638.html?ref=book-65152-9162_1-473554-0";
        String html = HttpUtils.getHtmlGetBycharSet(url, "gbk");
        //System.out.println(html);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();

                Elements types = document.select("div.messbox_info>span.t1");
                if (types != null && types.size() > 0) {


                    for (Element element : types) {
                        System.out.println(element.text());

                    }


                }

                Element content = document.getElementById("comm_num_down");
                System.out.println(content.text());

                Element info = document.getElementById("content").getElementsByTag("textarea").first();
                System.out.println(info.text());
            }
            Elements types = document.select("span.lie");
            if (types != null && types.size() > 0) {


                for (Element element : types) {
                    System.out.println(element.text());

                }


            }

            Elements images = document.getElementById("largePicDiv").getElementsByTag("img");
            if (images != null && images.size() > 0) {
                Element content = images.first();
                System.out.println(content.attr("src"));
            }

        }
    }


    public static void main(String[] args) {
        String url = "https://book.douban.com/subject/25733653/";
        String html = HttpUtils.getHtmlGetBycharSet(url, "utf-8");

        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Elements elements=document.getElementsByAttributeValue("name","keywords");
                Element element=elements.first();
                String content=element.attr("content");
                if(StringUtils.isNotEmpty(content))
                {
                    String arr[]=content.split(",");
                    int size=arr.length;
                    if(size>5)
                    {
                        bean.setTitle(arr[0]);
                        bean.setAuthor(arr[1]);
                        bean.setPress(arr[2]);
                        bean.setPushTime(arr[3]);
                    }

                }
            }
        }
    }
}






