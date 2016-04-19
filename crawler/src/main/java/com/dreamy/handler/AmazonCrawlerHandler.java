package com.dreamy.handler;


import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
//                Elements infos = document.select("div.content >ul>li");
//                if (infos != null && infos.size() > 0) {
//                    Element title = infos.first();
//                    if (title != null) {
//                        String content = title.text();
//                        if (StringUtils.isNotEmpty(content)) {
//                            String arr[] = content.split(";");
//
//                            bean.setPress(arr[0].replace("出版社:", ""));
//                            bean.setPushTime(result(arr[1]));
//                        }
//                    }
//                }
//                Elements authos = document.getElementsByClass("author");
//                if (authos != null && authos.size() > 0) {
//                    String author = "";
//                    for (Element element : authos) {
//                        author = author + element.text();
//                    }
//                    bean.setAuthor(author);
//                }
//
//
//                getToClickNum(bean, document);
//                //编辑评论
//                String comment = document.getElementsByTag("noscript").text();
//                bean.setComment(comment);
//                getScore(bean, document);
//                getSalesRank(bean, document);
//                getType(bean, document);

                getName(bean, document);
                getAuthor(bean, document);
                getPressAndPublishTime(bean, document);
                getSaleSort(bean, document);
                getTotalCommentNumAndScore(bean, document);
                getCoverImg(bean, document);
                getIpDescription(bean, document);
                getCategories(bean, document);
                getEditorComments(bean, document);
                getTags(bean, document);
                getAuthorDescrition(bean, document);

                return bean;
            }

        }

        return null;
    }

    /**
     * 获取书名
     *
     * @param bean
     * @param document
     */
    private void getName(BookInfo bean, Document document) {
        bean.setTitle(document.getElementById("productTitle").text());
    }

    /**
     * 获取作者
     *
     * @param bean
     * @param document
     */
    private void getAuthor(BookInfo bean, Document document) {
        String author = "";

        Elements elements = document.getElementsByClass("author");
        for (Element element : elements) {
            author += element.child(0).text();
            if ("contribution".equals(element.child(1).className())) {
                author += element.child(1).child(0).text();
            }
        }

        bean.setAuthor(author);
    }


    private void getPressAndPublishTime(BookInfo bean, Document document) {
        String press = "";
        String publishTime = "";
        Element element = document.getElementById("detail_bullets_id");
        if (element != null) {
            Elements elements = element.select("div>ul>li");
            String[] data = elements.get(0).text().substring(4).split(";");
            press = data[0];
            String pressInfo = (data[1].split("\\("))[1];
            publishTime = pressInfo.substring(0, pressInfo.length() - 2);
            publishTime = publishTime.replaceFirst("年", "-");
            publishTime = publishTime.replaceFirst("月", "-");

        }
        bean.setPushTime(publishTime);
        bean.setPress(press);
    }


    private void getTotalCommentNumAndScore(BookInfo bean, Document document) {
        Element element = document.getElementById("summaryStars").getElementsByTag("a").first();
        String[] scoreAndComment = element.text().split("星");
        Integer commentNumString = Integer.parseInt(scoreAndComment[1].replaceFirst(",", "").substring(1));
        bean.setCommentNum(commentNumString);
        bean.setScore(scoreAndComment[0].substring(2, scoreAndComment[0].length() - 1));
    }

    private void getSaleSort(BookInfo bean, Document document) {
        Element sort = document.getElementById("SalesRank");
        String[] saleRank = sort.childNode(2).toString().split("第");

        bean.setSaleSort(saleRank[1].substring(0, saleRank[1].length() - 3));
    }

    private void getCoverImg(BookInfo bean, Document document) {
        String imageUrl = "";
        Element element = document.getElementById("imgBlkFront");
        String dynamicImages = element.attr("data-a-dynamic-image");
        imageUrl = (dynamicImages.split("\""))[1];
        bean.setImage(imageUrl);
    }

    private void getIpDescription(BookInfo bean, Document document) {
        String description = "";
        Elements noscripts = document.getElementsByTag("noscript");
        if (noscripts.size() > 2) {
            description = noscripts.get(1).text();
        }

        bean.setInfo(description);
    }

    private void getCategories(BookInfo bean, Document document) {
        Map<String, String> categories = new HashMap<String, String>();
        Element element = document.getElementById("SalesRank");
        if (element != null) {
            Elements elements = element.getElementsByClass("zg_hrsr_item");
            Integer size = elements.size();
            if (size > 0) {
                for (int i = 0; i < size - 1; i++) {
                    String[] rankString = elements.get(i).child(0).text().split("第");
                    String rank = rankString[1].substring(0, rankString[1].length() - 1);

                    String parentCategory = elements.get(i).child(1).child(1).text();
                    String subParentCategory = elements.get(i).child(1).child(2).text();
                    categories.put(parentCategory + "|" + subParentCategory, rank);
                }
            }
        }

        bean.setCategories(categories);
    }

    private void getEditorComments(BookInfo bean, Document document) {
        String comment = "";
        Element element = document.getElementById("s_content_0");
        if (element != null) {
            comment = element.child(1).text();
        }

        bean.setEditorComment(comment);
    }

    private void getTags(BookInfo bean, Document document) {


    }

    private void getAuthorDescrition(BookInfo bean, Document document) {
//        String authorInfo = "";
//        Element element = document.getElementById("s_content_2");
//        if (element != null) {
//            authorInfo = element.child(1).text();
//        }
//
//        bean.setAuthorInfo(authorInfo);
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

}
