package com.dreamy.handler;

import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Component
public class DangDangCrawlerHandler extends AbstractCrawlerHandler {

    private final static String chromeDriverPath = "/usr/local/Cellar/chromedriver/2.21/bin/chromedriver";

    @Override
    public Integer getId() {
        return CrawlerSourceEnums.dangdang.getType();
    }

    @Override
    public BookInfo getByUrl(String url) {

        String html = HttpUtils.getHtmlGetBycharSet(url, "gbk");
        BookInfo bean = null;
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                bean = new BookInfo();
                //作品内容
                Elements contents = document.getElementById("content").getElementsByTag("textarea");
                if (contents != null && contents.size() > 0) {
                    Element content = contents.first();
                    bean.setInfo(content.text());
                }
                //编辑评论
                Elements infos = document.getElementById("abstract").getElementsByTag("textarea");
                if (infos != null && infos.size() > 0) {
                    Element content = infos.first();
                    bean.setEditorComment(content.text());
                }
                //作者信息
                Elements authorintro = document.getElementById("authorintro").getElementsByTag("textarea");
                if (authorintro != null && authorintro.size() > 0) {
                    Element content = authorintro.first();
                    bean.setAuthorInfo(content.text());
                }
                //图片
                Element image = document.getElementById("largePic");
                if (image != null) {
                    bean.setImage(image.attr("src"));
                }
                getAuthor(bean, document);
                getClickNum(bean, document);
                getCategories(bean, document);
                getTitle(bean, document);
                saleSort(bean, document);
                getScore(bean, document);
            }


        }
        return bean;

    }

    /**
     * 解析作者 出版社 出版时间
     *
     * @param bookInfo
     * @param document
     */
    private void getAuthor(BookInfo bookInfo, Document document) {
        Elements content = document.select("div.messbox_info>span.t1");
        if (content != null && content.size() > 0) {
            int size = content.size();
//            if (size >= 1) {
//                bookInfo.setAuthor(content.get(0).text());
//            }
//            if (size >= 2) {
//                bookInfo.setPress(content.get(1).text());
//            }
            if (size >= 3) {
                bookInfo.setPushTime(date(content.get(2).text()));
            }

        }
    }

    private void getClickNum(BookInfo bookInfo, Document document) {
        Element content = document.getElementById("comm_num_down");
        if (content != null) {
            bookInfo.setCommentNum(Integer.parseInt(content.text()));
        }
    }


    private void getCategories(BookInfo bookInfo, Document document) {
        Elements types = document.select("span.lie");
        StringBuffer infos = new StringBuffer();
        if (types != null && types.size() > 0) {
            for (Element element : types) {
                infos.append(element.text() + ",");

            }

            String str=infos.toString();
            bookInfo.setCategories(str.substring(0,str.length()-1)); 
        }

    }


    /**
     * 解析作者 出版社 标题
     *
     * @param bookInfo
     * @param document
     */
    private void getTitle(BookInfo bookInfo, Document document) {
        Elements elements = document.getElementsByAttributeValue("name", "keywords");
        Element element = elements.first();
        String content = element.attr("content");
        if (StringUtils.isNotEmpty(content)) {
            String arr[] = content.split(",");
            int size = arr.length;
            if (size > 3) {
                bookInfo.setTitle(arr[0]);
                bookInfo.setAuthor(arr[1]);
                bookInfo.setPress(arr[2]);
            }

        }
    }

    /**
     * 销售排名
     *
     * @param bookInfo
     * @param document
     */
    public void saleSort(BookInfo bookInfo, Document document) {
        Element element = document.getElementById("pid_span");
        if (element != null) {
            String product_id = element.attr("product_id");
            String url = " http://product.dangdang.com/pricestock/callback.php?type=getpublishbangv2&product_id=" + product_id;
            String result = HttpUtils.getHtmlGetBycharSet(url, "gbk");
            String str[] = result.split(";");
            if (str != null && str.length > 1) {
                String s = str[str.length - 1];
                String info = PatternUtils.getNum(s);
                bookInfo.setSaleSort(info);
            }


        }
    }

    /**
     * 评分
     *
     * @param bookInfo
     * @param document
     */
    public void getScore(BookInfo bookInfo, Document document) {
        Element element = document.getElementById("pid_span");
        if (element != null) {
            String product_id = element.attr("product_id");
            String url = "http://product.dangdang.com/comment/comment.php?product_id=" + product_id + "&datatype=1&page=1&filtertype=1&sysfilter=1";

            String result = HttpUtils.getHtmlGetBycharSet(url, "gbk");
            if (StringUtils.isNotEmpty(result)) {
                Map<String, Object> map1 = JsonUtils.toMap(result);
                if (map1 != null) {
                    Map<String, Object> map2 = (Map<String, Object>) map1.get("rateInfo");
                    if (map2 != null) {
                        String core = map2.get("good_rate").toString();
                        bookInfo.setScore(core);
                    }
                }
            }


        }
    }


    public static String date(String content) {
        String result = "";
        Pattern p = Pattern
                .compile("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/]");
        Matcher m = p.matcher(content);
        while (m.find()) {
            if (!"".equals(m.group())) {
                result = m.group();
            }
        }
        return result;
    }


    @Override
    public String analyeUrl(String url) {
        return null;
    }

    public static void main(String[] args) {

    }


}
