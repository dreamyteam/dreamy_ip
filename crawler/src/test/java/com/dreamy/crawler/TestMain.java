package com.dreamy.crawler;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import com.mongodb.util.JSON;
import com.rabbitmq.tools.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;
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


    public static void main(String[] args) {
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

            Elements types2 = document.getElementsByClass("breadcrumb");
            if (types2 != null && types2.size() > 0) {

                for (Element element : types2) {
                    System.out.println(element.text());

                }
            }
            Element element = document.getElementById("pid_span");


            if (element != null) {
                String product_id = element.attr("product_id");
                String url1 = " http://product.dangdang.com/pricestock/callback.php?type=getpublishbangv2&product_id=" + product_id;
                String aa = HttpUtils.getHtmlGetBycharSet(url1,"gbk");
                String str[]=aa.split(";");


                String s=str[str.length-1];
                String regex = "<span class=\"num\">1</span>位";
                String newStr = "";
                String s1 = s.replaceAll(regex, newStr);
                System.out.println(s1);

                String url11="http://product.dangdang.com/comment/comment.php?product_id="+product_id+"&datatype=1&page=1&filtertype=1&sysfilter=1";

                String result = HttpUtils.getHtmlGetBycharSet(url11, "gbk");
                Map<String,Object> map= JsonUtils.toMap(result);
                Map<String,Object> map1=(Map<String,Object> )map.get("rateInfo");
                System.out.println(map1.get("good_rate"));
            }




        }
    }


    public static void mainD(String[] args) {
        String url = "https://book.douban.com/subject/1770782/";
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
                Elements elementsss = document.getElementById("mainpic").getElementsByTag("img");
                if(elements!=null&&elementsss.size()>0)
                {
                    Element element1=elementsss.first();
                    System.out.println(element1.attr("src"));
                }

                Element elements1 = document.getElementsByClass("rating_people").first();
                System.out.println(elements1.text()+"121212");

            }

            Elements elements = document.select("div.intro");
            if(elements!=null&&elements.size()>0)
            {
                for(Element element:elements)
                {
                    System.out.println(element.text());
                }

            }

            Elements elementss = document.select("div.blank20>div.indent>span>a");
            if(elementss!=null&&elementss.size()>0)
            {
                for(Element element:elementss)
                {
                    System.out.println(element.text());
                }

            }

            Element elements1 = document.select("div.rating_wrap>div.rating_self>strong").first();
            System.out.println(elements1.text());


            }
        }







}






