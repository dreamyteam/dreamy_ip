package com.dreamy.crawler.handler.info.netbook.qd;

import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/5/31.
 */
@Component
public class QiDianHandler {

    private static final Logger log = LoggerFactory.getLogger(QiDianHandler.class);

    public NetBookInfo crawler(Integer bookId, String url, String operation) {
        NetBookInfo info = null;
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                info = new NetBookInfo();
                info.setBookId(bookId);
                String code = PatternUtils.getNum(url);
                info.setCode(Integer.valueOf(code));
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    getInfo(info, document);
                    getCategory(info, document);
                    getLabel(info, document);
                }
                getOverInfo(info, document);
                getAuthorUrl(info, document);
                getOverAuthority(info, document);
                getCommentNum(info, document);
                getClickNum(info, document);
                getRecommendNum(info, document);
                getScore(info, document);
                getTicketNum(info, document);
            }
        } else {
            log.info(" qidian  crawler is empty url=" + url + " book=" + bookId);
        }
        return info;
    }

    /**
     * 解析 简介
     *
     * @param info
     * @param document
     */
    private void getInfo(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementById("contentdiv").getElementsByAttributeValue("itemprop", "description");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                info.setInfo(element.text().replace(" ", ""));
            }
        } catch (Exception e) {
            log.error(" qidian 简介 is error book=" + info.getBookId(), e);

        }


    }

    /**
     * 解析 评论人数
     *
     * @param info
     * @param document
     */
    private void getCommentNum(NetBookInfo info, Document document) {

        Element element = document.getElementById("div_pingjiarenshu");
        if (element != null) {
            String num = PatternUtils.extracte("评价人数:(.*?)人", element.text());
            info.setCommentNum(Integer.valueOf(num));
        }


    }


    /**
     * 解析 作者链接
     *
     * @param info
     * @param document
     */
    private void getAuthorUrl(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementsByAttributeValue("itemprop", "author");
            if (elements != null && elements.size() > 0) {
                Elements element = elements.first().getElementsByAttributeValue("itemprop", "url");
                if (element != null) {
                    String url = element.attr("href");
                    String html = HttpUtils.getHtmlGet(url);
                    Document urlDocument = Jsoup.parse(html);
                    if (urlDocument != null) {
                        Elements elements1 = urlDocument.getElementsByAttributeValue("title", "起点ID");
                        if (elements1 != null && elements1.size() > 0) {
                            Element element1 = elements1.first();
                            String id = PatternUtils.getNum(element1.text());
                            String authorUrl = "http://t.qidian.com/Friend/HisFollower.php?id=" + id;
                            info.setAuthorUrl(authorUrl);


                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取 总点击数
     *
     * @param info
     * @param document
     */
    private void getClickNum(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "totalClick");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String num = element.text() != null ? element.text() : "0";
            info.setClickNum(Integer.valueOf(num));
        }


    }

    /**
     * 获取总推荐数
     *
     * @param info
     * @param document
     */
    private void getRecommendNum(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "totalRecommend");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String num = element.text() != null ? element.text() : "0";
            info.setRecommendNum(Integer.valueOf(num));
        }
    }

    /***
     * 获取是否完结信息
     *
     * @param info
     * @param document
     */
    private void getOverInfo(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByAttributeValue("itemprop", "updataStatus");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setOver(element.text());
        }


    }

    /**
     * 获取分类
     *
     * @param info
     * @param document
     */
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
        if (StringUtils.isNotEmpty(str)) {
            info.setLabel(str.substring(0, str.length() - 1));
        }


    }

    /**
     * 获取签约信息
     *
     * @param info
     * @param document
     */
    private void getOverAuthority(NetBookInfo info, Document document) {

        Elements elements = document.getElementById("bookdiv").getElementsByTag("tr");
        if (elements != null && elements.size() > 0) {
            Element element = elements.last();
            Elements tds = element.children();
            if (tds != null && tds.size() > 2) {
                Element td = tds.get(2);
                info.setAuthority(td.text().replace("授权状态：", "").replace(" ", ""));
            }
        }


    }

    /**
     * 获取评分
     *
     * @param info
     * @param document
     */
    private void getScore(NetBookInfo info, Document document) {
        Element element = document.getElementById("bzhjshu");
        if (element != null) {
            info.setScore(element.text());
        }
    }

    /**
     * 获取月票数
     *
     * @param info
     * @param document
     */
    private void getTicketNum(NetBookInfo info, Document document) {
        Elements elements = document.getElementsByClass("ballot_data");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String num = PatternUtils.extracte("本月票数：(.*?)票", element.text());
            info.setTicketNum(Integer.valueOf(num));
        }
    }


}
