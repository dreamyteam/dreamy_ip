package com.dreamy.handler;


import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AmazonCrawlerHandler extends AbstractCrawlerHandler {

    @Override
    public Integer getId() {
        return CrawlerSourceEnums.amazon.getType();
    }

    @Override
    public BookInfo getByUrl(String url) {
        url=HttpUtils.toUtf8String(url);
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Elements infos = document.select("div.content >ul>li");
                if (infos != null && infos.size() > 0) {

                    Element title = infos.first();
                    if (title != null) {
                        String content = title.text();
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
                    bean.setAuthor(author);
                }


                getToClickNum(bean, document);
                //编辑评论
                String comment = document.getElementsByTag("noscript").text();
                bean.setComment(comment);
                getScore(bean, document);
                getSalesRank(bean, document);
                getType(bean, document);


            }


        }


        return null;
    }

    /**
     * 总评论
     *
     * @param bean
     * @param document
     */
    private void getToClickNum(BookInfo bean, Document document) {
        Element content = document.getElementById("summaryStars").getElementsByTag("a").first();
        bean.setClickNum(content.childNode(2).toString());

    }

    /**
     * 评分
     *
     * @param bean
     * @param document
     */
    private void getScore(BookInfo bean, Document document) {
        Element score = document.getElementById("avgRating");
        bean.setScore(score.text());

    }

    /**
     * 排名
     *
     * @param bean
     * @param document
     */
    private void getSalesRank(BookInfo bean, Document document) {
        Element sort = document.getElementById("SalesRank");
        bean.setSaleSort(sort.childNode(2).toString().replace("(", ""));


    }

    /**
     * 作品类型
     *
     * @param bean
     * @param document
     */
    private void getType(BookInfo bean, Document document) {
        Elements types = document.select("ul.zg_hrsr>li");
        StringBuffer typeInfo = new StringBuffer();
        if (types != null && types.size() > 0) {

            for (Element element : types) {
                typeInfo.append(element.text() + ",");
            }

            bean.setType(typeInfo.toString());
        }

    }


    @Override
    public String analyeUrl(String url) {
        return null;
    }


    public static String result(String content) {
        String result = "";
        Pattern p = Pattern
                .compile("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}");
        Matcher m = p.matcher(content);
        while (m.find()) {
            if (!"".equals(m.group())) {
                result = m.group();
                result = result.replaceAll("年", "-");
                result = result.replaceAll("月", "-");
                result = result.replaceAll("/", "-");

            }
        }
        return result;
    }


}
