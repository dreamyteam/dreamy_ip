package com.crawler.test;

import com.dreamy.crawler.handler.info.netbook.qd.QiDian;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/5/30.
 */
public class QiDianMian {
    public static void main(String[] args) {
        //String url = "http://top.qidian.com/Book/TopDetail.aspx?TopType=3&PageIndex=1";
        String url = "http://all.qidian.com/Book/BookStore.aspx?ChannelId=-1&SubCategoryId=-1&Tag=all&Size=-1&Action=-1&OrderId=6&P=all&PageIndex=1&update=-1&Vip=1&Boutique=-1&SignStatus=-1";
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), QiDian.class);
        QiDian qiDian = ooSpider.<QiDian>get(url);
        NetBookInfo info = new NetBookInfo();
        int size = qiDian.getUrls().size();
        List<String> urls = qiDian.getUrls();
        List<String> names = qiDian.getTitles();
        List<String> authoers = qiDian.getAuthoers();
        for (int i = 0; i < size; i++) {
            String utl = urls.get(i);
            info.setTitle(names.get(i));
            info.setAuthor(authoers.get(i));
            String bookId = PatternUtils.getNum(utl);
            info.setImage("http://image.cmfu.com/books/" + bookId + "/" + bookId + ".jpg");
            info.setBookId(Integer.valueOf(bookId));
            crawler(info, utl);
        }
    }


    public static void crawler(NetBookInfo info, String url) {

        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                getInfo(info, document);
                getClickNum(info, document);
                getRecommendNum(info, document);
                getCommentNum(info, document);
                getAuthorUrl(info, document);
                getOverInfo(info, document);
                getOverAuthority(info, document);
                getScore(info, document);
                getTicketNum(info, document);
                getLabel(info, document);
                getCategory(info, document);
            }
        }

    }

    public static void getInfo(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("contentdiv").getElementsByAttributeValue("itemprop", "description");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setInfo(element.text());
        }


    }

    public static void getCommentNum(NetBookInfo info, Document document) {

        Element element = document.getElementById("div_pingjiarenshu");
        if (element != null) {

            String num = getResult("评价人数:(.*?)人", element.text());
            System.out.println(num);
        }


    }


    public static void getAuthorUrl(NetBookInfo info, Document document) {

        Elements elements = document.getElementsByAttributeValue("itemprop", "author");
        if (elements != null && elements.size() > 0) {
            Elements element = elements.first().getElementsByAttributeValue("itemprop", "url");
            if (element != null) {
                info.setAuthorUrl(element.attr("href"));
            }

        }


    }

    public static void getClickNum(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "totalClick");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String num = element.text() != null ? element.text() : "0";
            info.setClickNum(Integer.valueOf(num));
        }


    }

    public static void getRecommendNum(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "totalRecommend");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String num = element.text() != null ? element.text() : "0";
            info.setRecommendNum(Integer.valueOf(num));
        }
    }

    public static void getOverInfo(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "updataStatus");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setOver(element.text());
        }


    }


    public static void getCategory(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "genre");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setCategory(element.text());
        }


    }

    /**
     * 作者 自定义标签
     *
     * @param info
     * @param document
     */
    public static void getLabel(NetBookInfo info, Document document) {

        StringBuffer labels = new StringBuffer();
        Elements elements = document.select("div.labels>div.box>a");
        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                labels.append(element.text() + ",");
            }
        }

        String str = labels.toString();
        info.setLabel(str.substring(0, str.length() - 1));


    }


    public static void getOverAuthority(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByTag("tr");
        if (elements != null && elements.size() > 0) {
            Element element = elements.last();
            Elements tds = element.children();
            if (tds != null && tds.size() > 2) {
                Element td = tds.get(2);
                info.setAuthority(td.text().replace("授权状态：", "").replace(" ", ""));
                System.out.println(td.text().replace("授权状态：", "").replace(" ", ""));
            }
        }


    }

    public static void getScore(NetBookInfo info, Document document) {

        Element element = document.getElementById("bzhjshu");
        if (element != null) {
            info.setScore(element.text());
        }
    }


    public static void getTicketNum(NetBookInfo info, Document document) {

        Elements elements = document.getElementsByClass("ballot_data");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String num = getResult("本月票数：(.*?)票", element.text());
            info.setTicketNum(Integer.valueOf(num));
        }
    }


    public static String getResult(String reg, String str) {
        String result = "0";
        //"总点击：([0-9]*)"
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        while (m.find()) {
            result = m.group(1);
        }
        if (StringUtils.isEmpty(result)) {
            return "0";
        }
        return result;

    }
}
