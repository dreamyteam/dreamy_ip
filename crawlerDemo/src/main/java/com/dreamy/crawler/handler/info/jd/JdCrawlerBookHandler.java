package com.dreamy.crawler.handler.info.jd;

import com.dreamy.crawler.selenium.SeleniumDownloader;
import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookInfo;

import com.dreamy.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Component
public class JdCrawlerBookHandler {
    private static final Logger log = LoggerFactory.getLogger(JdCrawlerBookHandler.class);

    public BookInfo getByISBN(String isbn, String operation) {
        BookInfo bookInfo = null;
        //String url = "http://search.jd.com/Search?keyword=" + isbn + "&enc=utf-8&pvid=x00em9oi.5otj5i";
        String url = "http://search.jd.com/Search?keyword=" + isbn + "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&psort=4&click=0";
        String html = HttpUtils.getHtmlGet(url);
        Document document = Jsoup.parse(html);
        Elements books = document.select("div.goods-list-v1>ul.gl-warp>li.gl-item>div.gl-i-wrap>div.p-img>a");
        if (books != null && books.size() > 0) {
            String crawlerUrl = books.get(0).attr("href");
            crawlerUrl = "http:" + crawlerUrl + "#comment";
            bookInfo = crawler(crawlerUrl, operation);
            return bookInfo;
        }

        return bookInfo;
    }


    public BookInfo crawler(String url, String operation) {
        String html = HttpUtils.getHtmlGet(url,"gbk");//seleniumDownloader(url);
        BookInfo bean = null;
        String code = PatternUtils.getNum(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                bean = new BookInfo();
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    bean.setUrl(url);
                    author(bean, document);
                    pushTime(bean, document);
                    imageAndTitle(bean, document);
                    infoAndAuthorInfo(bean, document);
                }
                saleSort(bean, code);
                score(bean, code);

            }


        }
        return bean;

    }

    /**
     * 解析平台销售排名
     *
     * @param bean
     * @param code
     */
    private void saleSort(BookInfo bean, String code) {
//        Element element = document.getElementById("summary-order");
//        if (element != null) {
//            String sort = PatternUtils.getNum(element.text());
//            bean.setSaleSort(sort);
//        }
        int sort = 0;
        String url = "http://c.3.cn/book?skuId=" + code + "&cat=1713,3258,3320&area=1_72_2799_0";
        String json = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Integer> map = JsonUtils.toMap(json);
            int rank = map.get("rank");
            if (rank > 0) {
                sort = rank;
            }

        }
        bean.setSaleSort(sort);


    }

    /**
     * 总评价数
     *
     * @param bean
     * @param document
     */
    private void commentNum(BookInfo bean, Document document) {
        Element element = document.getElementById("comment-count");
        if (element != null) {
            String result = PatternUtils.getNum(element.text());
            if (StringUtils.isNotEmpty(result)) {
                bean.setCommentNum(Integer.valueOf(result));
            }
        }

    }

    /**
     * 平台评分 总评价数
     *
     * @param bean
     * @param code
     */
    private void score(BookInfo bean, String code) {
//        Elements elements = document.getElementsByClass("rate");
//        if (elements != null && elements.size() > 0) {
//            Element element = elements.first();
//            String score = PatternUtils.getNum(element.text());
//            bean.setScore(score);
//        }
        String url = "http://club.jd.com/clubservice.aspx?method=GetCommentsCount&referenceIds=" + code;
        String json = HttpUtils.getHtmlGet(url);
        int commentNum = 0;
        double score = 0.0;
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Object> map = JsonUtils.toMap(json);
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("CommentsCount");
            if (CollectionUtils.isNotEmpty(list)) {
                Map<String, Object> map1 = list.get(0);
                commentNum = (Integer) map1.get("CommentCount");
                score = (Double) map1.get("GoodRate") * 100;
                bean.setScore(score);
                bean.setCommentNum(commentNum);


            }


        }


    }

    /**
     * /**
     * 解析 图片和标题
     *
     * @param bean
     * @param document
     */
    private void imageAndTitle(BookInfo bean, Document document) {
        Element image = document.getElementById("spec-n1").getElementsByTag("img").first();
        if (image != null) {
            bean.setImage("http:" + image.attr("src"));
            bean.setTitle(image.attr("alt"));
        }
    }

    /**
     * 解析作者
     *
     * @param bean
     * @param document
     */
    private void author(BookInfo bean, Document document) {
        Element element = document.getElementById("p-author");
        if (element != null) {
            bean.setAuthor(element.text());
        }
    }

    /**
     * 解析出版社 出版时间
     *
     * @param bookInfo
     * @param document
     */
    private void pushTime(BookInfo bookInfo, Document document) {
        Elements types = document.select("ul.p-parameter-list>li");
        if (types != null && types.size() > 0) {
            for (Element element : types) {
                String coment = element.text().replace("：", ":");
                String arr[] = coment.split(":");
                if (arr.length > 1)
                    if (arr[0].equals("出版社")) {
                        bookInfo.setPress(arr[1]);
                    } else if (arr[0].equals("ISBN")) {
                        bookInfo.setISBN(arr[1]);
                    } else if (arr[0].equals("出版时间")) {
                        bookInfo.setPushTime(arr[1]);
                    }
            }


        }


    }

    /**
     * 作品简介 作者信息
     *
     * @param bookInfo
     * @param document
     */
    private void infoAndAuthorInfo(BookInfo bookInfo, Document document) {
        Elements comments = document.getElementsByClass("book-detail-item");
        if (comments != null && comments.size() > 0) {
            for (Element element : comments) {
                String name = element.attr("text");
                if (name.equals("编辑推荐")) {
                    bookInfo.setEditorComment(element.text().replace(name, ""));
                } else if (name.equals("内容简介")) {
                    bookInfo.setInfo(element.text().replace(name, ""));
                } else if (name.equals("作者简介")) {
                    bookInfo.setAuthorInfo(element.text().replace(name, ""));
                }
            }
        }
    }


    private String seleniumDownloader(String url) {
        SeleniumDownloader seleniumDownloader = new SeleniumDownloader();
        String html = "";
        try {
            Page page = seleniumDownloader.download(new Request(url), new Task() {
                @Override
                public String getUUID() {
                    return "http://www.jd.com/";
                }

                @Override
                public Site getSite() {
                    return Site.me();
                }
            });

            html = page.getRawText();
        } catch (Exception e) {
            log.error("JdCrawlerBookHandler is error  url:" + url, e);
        } finally {
            seleniumDownloader.close();
            return html;
        }


    }

    public static void main(String[] args) {
        String url = "http://c.3.cn/book?skuId=11909886&cat=1713,3258,3320&area=1_72_2799_0";
        url = "http://club.jd.com/clubservice.aspx?method=GetCommentsCount&referenceIds=11909886";
        String json = HttpUtils.getHtmlGet(url);
        System.out.println(json);
        Map<String, Object> map = JsonUtils.toMap(json);
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("CommentsCount");
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map1 = list.get(0);
            int commentNum = (Integer) map1.get("CommentCount");
            double score = (Double) map1.get("GoodRate");


        }
    }


}
