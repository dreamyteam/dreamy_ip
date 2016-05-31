package com.dreamy.crawler.handler.info.douban;

import com.dreamy.enums.OperationEnums;
import com.dreamy.enums.RedisConstEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Component
public class DouBanCrawlerBookHandler {

    private static final Logger log = LoggerFactory.getLogger(DouBanCrawlerBookHandler.class);
    @Value("${crawler_proxy}")
    private Boolean proxy;

    @Autowired
    private ListOperations<String, String> listOperations;

    public BookInfo crawler(String url, String operation) {
        BookInfo bean = null;
        boolean check = false;
        if (proxy) {
            while (true) {
                String value = listOperations.leftPop(RedisConstEnums.proxIpList.getCacheKey());
                if (StringUtils.isEmpty(value)) {
                    break;
                }
                bean = new BookInfo();
                check = crawleringByProxy(operation, bean, url, value);
                if (check) {
                    listOperations.leftPush(RedisConstEnums.proxIpList.getCacheKey(), value);
                    break;
                }
            }
        } else {
            bean = new BookInfo();
            crawlering(operation, bean, url);
        }


        return bean;

    }

    private void crawlering(String operation, BookInfo bean, String url) {
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    getAuthorAndPressAndPublishTime(bean, document);
                    getAuthorInfo(bean, document);
                    getImage(bean, document);
                    getTags(bean, document);
                    getISBN(bean, document);
                }
                getCommentNum(bean, document);
                getScore(bean, document);

            }
        }
    }

    private boolean crawleringByProxy(String operation, BookInfo bean, String url, String value) {
        String arr[] = value.split(":");
        String hostname = arr[0];
        int port = Integer.valueOf(arr[1]);
        String html = HttpUtils.getHtmlGetByProxy(url, hostname, port, null);
        if (StringUtils.isNotEmpty(html) && !html.equals("400")) {
            if (html.equals(HttpStatus.SC_FORBIDDEN)) {
                return false;
            } else {
                Document document = Jsoup.parse(html);
                if (document != null) {
                    if (operation.equals(OperationEnums.crawler.getCode())) {
                        getAuthorAndPressAndPublishTime(bean, document);
                        getImage(bean, document);
                        getAuthorInfo(bean, document);
                        getTags(bean, document);
                        getCommentNum(bean, document);
                    }
                    getScore(bean, document);
                    getISBN(bean, document);

                }
                return true;
            }
        }
        return false;
    }


    /**
     * 解析ISBN
     *
     * @param bookInfo
     * @param document
     */

    private void getISBN(BookInfo bookInfo, Document document) {
        try {
            Element element = document.getElementById("info");
            if (element != null) {
                String str = element.text().replace(" ", "");
                Pattern p = Pattern.compile("ISBN:([0-9]*)");
                Matcher m = p.matcher(str);
                String result = "";
                while (m.find()) {
                    result = m.group(1);
                }
                bookInfo.setISBN(result);
            }
        } catch (Exception e) {
            log.error("解析 ISBN 异常", e);

        }
    }

    /**
     * 解析作者 出版社 出版时间
     *
     * @param bookInfo
     * @param document
     */
    private void getAuthorAndPressAndPublishTime(BookInfo bookInfo, Document document) {
        Elements elements = document.getElementsByAttributeValue("name", "keywords");
        Element element = elements.first();
        String content = element.attr("content");
        if (StringUtils.isNotEmpty(content)) {
            String arr[] = content.split(",");
            int size = arr.length;
            if (size > 5) {
                bookInfo.setTitle(arr[0]);
                bookInfo.setAuthor(arr[1]);
                bookInfo.setPress(arr[2]);
                bookInfo.setPushTime(arr[3]);
            }

        }
    }


    /**
     * 解析图片
     *
     * @param bookInfo
     * @param document
     */
    private void getImage(BookInfo bookInfo, Document document) {
        Elements elements = document.getElementById("mainpic").getElementsByTag("img");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            bookInfo.setImage(element.attr("src"));
        }


    }

    /**
     * 解析 作者简介 内容简介
     *
     * @param bookInfo
     * @param document
     */
    public void getAuthorInfo(BookInfo bookInfo, Document document) {
        try {
            Elements elements = document.select("div.intro");
            if (elements != null && elements.size() > 0) {
                Element element = elements.get(0);
                bookInfo.setInfo(element.text());
                if (elements.size() > 1) {
                    element = elements.get(1);
                    bookInfo.setAuthorInfo(element.text());
                }
            }
        } catch (Exception e) {
            log.error("解析 作者简介 内容简介 异常", e);


        }


    }

    /**
     * 总评论
     *
     * @param bean
     * @param document
     */
    private void getCommentNum(BookInfo bean, Document document) {
        Elements elements = document.getElementsByClass("rating_people");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String str = element.text();
            bean.setCommentNum(Integer.parseInt(PatternUtils.getNum(str)));
        }


    }

    /**
     * 解析标签
     *
     * @param bookInfo
     * @param document
     */
    private void getTags(BookInfo bookInfo, Document document) {
        Elements elements = document.select("div.blank20>div.indent>span>a");
        StringBuffer tags = new StringBuffer();
        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                tags.append(element.text() + ",");

            }
            bookInfo.setTags(tags.toString());
        }

    }

    /**
     * 评分
     *
     * @param bookInfo
     * @param document
     */
    private void getScore(BookInfo bookInfo, Document document) {
        Elements elements = document.select("div.rating_wrap>div.rating_self>strong");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            bookInfo.setScore(element.text());
        }

    }


}
