package com.dreamy.crawler.handler.info.netbook.hy;

import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/6/2.
 */
@Component
public class HuaYuHandler {

    private static final Logger log = LoggerFactory.getLogger(HuaYuHandler.class);


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
                    getImage(info, document);
                    getInfo(info, document);
                    getLable(info, document);
                }
                getAuthority(info, document);
                getClickNum(info, document);
                getCommentNum(info, document);
                getTicketNum(info, document);
                getOverInfo(info, document);
            }
        }
        else{
            log.info(" huyua  crawler is empty url="+url+" book=" +bookId);
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
            Elements elements = document.getElementsByClass("jj");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                info.setInfo(element.text());
            }
        } catch (Exception e) {
            log.error(" huyua 简介 is error book=" + info.getBookId(), e);

        }
    }


    /**
     * 解析 作者标签
     *
     * @param info
     * @param document
     */
    private void getLable(NetBookInfo info, Document document) {
        try {
            StringBuffer labels = new StringBuffer();
            Elements elements = document.select("p>em>a");
            if (elements != null && elements.size() > 0) {
                for (Element element : elements) {
                    labels.append(element.text() + ",");
                }
                String str = labels.toString();
                info.setLabel(str.substring(0, str.length() - 1));
            }
        } catch (Exception e) {
            log.error(" huyua 作者标签 is error book=" + info.getBookId(), e);

        }
    }

    /**
     * 解析 图片
     *
     * @param info
     * @param document
     */
    private void getImage(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.img>a>img");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                info.setImage(element.attr("src"));
            }
        } catch (Exception e) {
            log.error(" huyua 图片地址 is error book=" + info.getBookId(), e);
        }
    }

    /**
     * 解析 评论数
     *
     * @param info
     * @param document
     */
    private void getCommentNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementsByClass("total_threads");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                String num=element.text()!=null?element.text():"0";
                info.setCommentNum(Integer.valueOf(num));
            }
        } catch (Exception e) {
            log.error(" huyua 评论数 is error book=" + info.getBookId(), e);

        }
    }


    /**
     * 解析 总点击数
     *
     * @param info
     * @param document
     */
    public static void getClickNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.book_click>span");
            if (elements != null && elements.size() > 0) {
                Element c = elements.get(0);
                info.setClickNum(Integer.valueOf(PatternUtils.getNum(c.text())));
                Element s = elements.get(2);
                info.setScore(PatternUtils.getNum(s.text()));

            }
        } catch (Exception e) {
            log.error(" huyua 总点击数 is error book=" + info.getBookId(), e);
        }
    }

    /**
     * 解析 总红票数
     *
     * @param info
     * @param document
     */
    public static void getTicketNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.getElementsByClass("booknumber");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                String tickeNum = getResult("总红票：([0-9]*)", element.text());
                info.setTicketNum(Integer.valueOf(tickeNum));
            }
        } catch (Exception e) {
            log.error(" huyua 总红票数 is error book=" + info.getBookId(), e);
        }
    }


    /**
     * 解析 授权信息
     *
     * @param info
     * @param document
     */
    private void getAuthority(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.lebg>h1>b");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                info.setAuthority(element.text());
            }
        } catch (Exception e) {
            log.error(" huyua 授权信息 is error book=" + info.getBookId(), e);
        }
    }

    /**
     * 解析 是否完结 信息
     *
     * @param info
     * @param document
     */
    private void getOverInfo(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.booktitle>div");
            if (elements != null && elements.size() > 2) {
                Element element = elements.get(1);
                String result = element.attr("class");
                if (result.equals("lzz")) {
                    info.setOver("连载中");
                } else if (result.equals("ywj")) {
                    info.setOver("已完结");
                }

            }
        } catch (Exception e) {
            log.error(" huyua 解析是否完结 is error book=" + info.getBookId(), e);

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
