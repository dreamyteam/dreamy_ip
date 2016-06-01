package com.dreamy.crawler.handler.info.netbook;

import com.dreamy.crawler.selenium.SeleniumDownloader;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.mogodb.dao.NetBookInfoDao;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

/**
 * Created by wangyongxing on 16/5/31.
 */
public class ZongHengHandler {

    NetBookInfoDao netBookInfoDao;
    public static void crawler(NetBookInfo info, String url) {
        //String html =seleniumDownloader(url);
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                getImage(info, document);
                getInfo(info, document);
                getClickNum(info, document);
                getTicketNum(info, document);
                getTicketSort(info, document);
            }
        }

    }

    public static void getImage(NetBookInfo info, Document document) {

        Elements elements = document.select("div.book_cover>p>a>img");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setImage(element.attr("src"));
        }


    }

    public static void getInfo(NetBookInfo info, Document document) {

        Elements elements = document.select("div.info_con>p");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setInfo(element.text());
        }

    }

    public static void getClickNum(NetBookInfo info, Document document) {

        Elements elements = document.select("div.vote_info>p");
        if (elements != null && elements.size() > 0) {
            Element element = elements.get(2);
            System.out.println(element.text());
            Element element1 = elements.get(4);
            System.out.println(element1.text());
        }

    }

    public static void getTicketNum(NetBookInfo info, Document document) {

        Element element = document.getElementById("bookticket");
        if (element != null) {
            info.setTicketNum(Integer.valueOf(PatternUtils.getNum(element.text())));
        }

    }

    public static void getTicketSort(NetBookInfo info, Document document) {

        Element element = document.getElementById("bookrank");
        if (element != null) {
            info.setMonthSort(PatternUtils.getNum(element.text()));
        }

    }


    public static String seleniumDownloader(String url) {
        SeleniumDownloader seleniumDownloader = new SeleniumDownloader();//("/usr/local/Cellar/chromedriver/2.21/bin/chromedriver");
        seleniumDownloader.setSleepTime(500);
        String html = "";
        try {
            Page page = seleniumDownloader.download(new Request(url), new Task() {
                @Override
                public String getUUID() {
                    return "http://book.zongheng.com";
                }

                @Override
                public Site getSite() {
                    return Site.me();
                }
            });

            html = page.getRawText();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            seleniumDownloader.close();
            return html;
        }

    }

}
