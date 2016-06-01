package com.dreamy.crawler.handler.info.netbook.qd;

import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.dao.NetBookInfoDao;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/5/31.
 */
public class QiDianHandler {

    NetBookInfoDao netBookInfoDao;
    public static void crawler(Integer bookId, String url) {

        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                NetBookInfo info =new NetBookInfo();
                info.setBookId(bookId);
                getInfo(info, document);
                getOverInfo(info, document);
                getOverAuthority(info, document);
                getClickNum(info, document);
                getRecommendNum(info, document);
                getScore(info, document);
                getTicketNum(info,document);
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

    public static void getOverAuthority(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByTag("tr");
        if (elements != null && elements.size() > 0) {
            Element element = elements.last();
            Elements tds=element.children();
            if(tds!=null&&tds.size()>2) {
                Element td = tds.get(2);
                info.setAuthority(td.text().replace("授权状态：","").replace(" ",""));
                System.out.println(td.text().replace("授权状态：","").replace(" ",""));
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
            String num=getResult(element.text());
            info.setTicketNum(Integer.valueOf(num));
        }
    }
    public static String getResult(String str) {
        String result = "0";
        Pattern p = Pattern.compile("本月票数：(.*?)票");
        Matcher m = p.matcher(str);
        while (m.find()) {
            result = m.group(1);
        }
        if(StringUtils.isEmpty(result))
        {
            return "0";
        }
        return result;

    }
}
