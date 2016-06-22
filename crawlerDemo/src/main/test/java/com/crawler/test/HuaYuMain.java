package com.crawler.test;

import com.dreamy.crawler.handler.info.netbook.hy.HuaYu;
import com.dreamy.crawler.handler.info.netbook.qd.QiDian;
import com.dreamy.crawler.handler.info.netbook.zh.ZongHeng;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.apache.xpath.axes.NodeSequence;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/6/2.
 */
public class HuaYuMain {

    public static void main11(String[] args) {
        String url = "http://huayu.baidu.com/store/c0/c0/u5/p1/v1/s0/ALL.html";
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), HuaYu.class);
        HuaYu huaYu = ooSpider.<HuaYu>get(url);
        System.out.println(111);
    }

    public static void main(String[] args) {
        String url = "http://book.zongheng.com/store/c0/c0/b1/u0/p1/v1/s9/t0/ALL.html";
        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), ZongHeng.class);
        ZongHeng zongHeng = ooSpider.<ZongHeng>get(url);
        NetBookInfo info = new NetBookInfo();
        int size = zongHeng.getUrls().size();
        List<String> urls = zongHeng.getUrls();
        List<String> names = zongHeng.getTitles();
        List<String> authoers = zongHeng.getAuthoers();
        for (int i = 0; i < size; i++) {
            crawler(info, urls.get(i));
            info.setTitle(names.get(i));
            info.setAuthor(authoers.get(i));
            System.out.println(urls.get(i) + "-" + names.get(i) + "...." + authoers.get(i));

        }
    }


    public static void crawler(NetBookInfo info, String url) {
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            getImage(info, document);
            getInfo(info, document);
            getClickNum(info, document);
            getAuthority(info, document);
            getCommentNum(info, document);
            getOverInfo(info, document);
            getTicketNum(info, document);
        }


    }

    /**
     * 解析 简介
     *
     * @param info
     * @param document
     */
    public static void getInfo(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementsByClass("jj");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                System.out.println(element.text());
                info.setInfo(element.text());
            }
        } catch (Exception e) {

        }
    }


    /**
     * 解析 作者标签
     *
     * @param info
     * @param document
     */
    public static void getLable(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("p>em>a");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                System.out.println(element.text());
                info.setInfo(element.text());

            }
        } catch (Exception e) {

        }
    }

    /**
     * 解析 图片
     *
     * @param info
     * @param document
     */
    public static void getImage(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.img>a>img");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                System.out.println(element.attr("src"));
                info.setImage(element.attr("src"));
            }
        } catch (Exception e) {

        }
    }

    /**
     * 解析 评论数
     *
     * @param info
     * @param document
     */
    public static void getCommentNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementsByClass("total_threads");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                System.out.println(element.text());
            }
        } catch (Exception e) {

        }
    }


    /**
     * 解析 评论数
     *
     * @param info
     * @param document
     */
    public static void getClickNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.book_click>span");
            if (elements != null && elements.size() > 0) {
                Element c= elements.get(0);
                System.out.println(c.text());
                Element s=elements.get(2);
                System.out.println(s.text());

            }
        } catch (Exception e) {

        }
    }
    public static void getTicketNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementsByClass("booknumber");
            if (elements != null && elements.size() > 0) {
                Element element=elements.first();
                String tickeNum=getResult("总红票：([0-9]*)",element.text());
                info.setTicketNum(Integer.valueOf(tickeNum));
            }
        } catch (Exception e) {

        }
    }



    /**
     * 解析 签约
     *
     * @param info
     * @param document
     */
    public static void getAuthority(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.lebg>h1>b");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                info.setAuthority(element.text());
            }
        } catch (Exception e) {

        }
    }

    /**
     * 解析 评论数
     *
     * @param info
     * @param document
     */
    public static void getOverInfo(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.booktitle>div");
            if (elements != null && elements.size() > 0) {
                Element element = elements.get(1);
                System.out.println(element.attr("class"));
            }
        } catch (Exception e) {

        }
    }

    public static String getResult(String reg,String str) {
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
