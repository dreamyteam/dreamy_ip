package com.dreamy.handler;


import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AmazonCrawlerHandler extends AbstractCrawlerHandler {
    private static final Logger log = LoggerFactory.getLogger(AmazonCrawlerHandler.class);

    @Override
    public Integer getId() {
        return CrawlerSourceEnums.amazon.getType();
    }

    @Override
    public BookInfo getByUrl(String url) {
        url = HttpUtils.toUtf8String(url);
        String html = HttpUtils.getHtmlGetByProxy(url,"119.29.149.105",16816,HttpUtils.USER_AGENT);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
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
        Element element = document.getElementById("productTitle");
        if (element != null) {
            bean.setTitle(element.text());
        }
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


    /**
     * 出版社和出版时间
     *
     * @param bean
     * @param document
     */
    private void getPressAndPublishTime(BookInfo bean, Document document) {
        try {
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
        catch (Exception e)
        {
            log.error("解析 出版社和出版时间 异常", e);
        }
    }


    /**
     * 总的评论数和平台评分
     *
     * @param bean
     * @param document
     */
    private void getTotalCommentNumAndScore(BookInfo bean, Document document) {
        Element element = document.getElementById("summaryStars").getElementsByTag("a").first();
        String[] scoreAndComment = element.text().split("星");
        Integer commentNumString = Integer.parseInt(scoreAndComment[1].replaceFirst(",", "").substring(1));
        bean.setCommentNum(commentNumString);
        bean.setScore(scoreAndComment[0].substring(2, scoreAndComment[0].length() - 1));
    }

    /**
     * 图书销售排名
     *
     * @param bean
     * @param document
     */
    private void getSaleSort(BookInfo bean, Document document) {
        try {
            Element sort = document.getElementById("SalesRank");
            String[] saleRank = sort.childNode(2).toString().split("第");

            bean.setSaleSort(saleRank[1].substring(0, saleRank[1].length() - 3));
        }
        catch (Exception e){
            log.error("解析 图书销售排名 异常", e);
        }

    }

    /**
     * 封面
     *
     * @param bean
     * @param document
     */
    private void getCoverImg(BookInfo bean, Document document) {
        try {
            String imageUrl = "";
            Element element = document.getElementById("imgBlkFront");
            String dynamicImages = element.attr("data-a-dynamic-image");
            imageUrl = (dynamicImages.split("\""))[1];
            bean.setImage(imageUrl);
        }
        catch (Exception e){
            log.error("解析 封面 异常", e);
        }
    }

    /**
     * 获取ip的描述
     *
     * @param bean
     * @param document
     */
    private void getIpDescription(BookInfo bean, Document document) {
        String description = "";
        Elements noscripts = document.getElementsByTag("noscript");
        if (noscripts!=null&&noscripts.size() > 2) {
            description = noscripts.get(1).text();
        }

        bean.setInfo(description);
    }

    /**
     * 获取分类
     *
     * @param bean
     * @param document
     */
    private void getCategories(BookInfo bean, Document document) {
        try {
            String categories = "";
            Element element = document.getElementById("SalesRank");
            if (element != null) {
                Elements elements = element.getElementsByClass("zg_hrsr_item");
                Integer size = elements.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        Element currentElement = elements.get(i);
                        String[] rankString = currentElement.child(0).text().split("第");
                        String rank = rankString[1].substring(0, rankString[1].length() - 1);

                        String parentCategory = currentElement.child(1).child(1).text();
                        if (currentElement.child(1).childNodes().size() > 5) {
                            String subParentCategory = currentElement.child(1).child(2).text();
                            categories += parentCategory + "|" + subParentCategory + ":" + rank + ",";
                        } else {
                            categories += parentCategory + ":" + rank + ",";
                        }
                    }

                    categories = categories.substring(0, categories.length() - 1);
                }
            }

            bean.setCategories(categories);
        }
        catch (Exception e){
            log.error("解析 获取分类 异常", e);
        }
    }

    /**
     * 编辑评论
     *
     * @param bean
     * @param document
     */
    private void getEditorComments(BookInfo bean, Document document) {
        String comment = "";
        Element element = document.getElementById("s_content_0");
        if (element != null) {
            comment = element.child(1).text();
        }

        bean.setEditorComment(comment);
    }


    /**
     * 标签
     *
     * @param bean
     * @param document
     */
    private void getTags(BookInfo bean, Document document) {


    }

    /**
     * 获取作者描述
     *
     * @param bean
     * @param document
     */
    private void getAuthorDescrition(BookInfo bean, Document document) {
        try {
        String authorDescription = "";
        Element element = document.getElementById("detail_bullets_id");
        if (element != null) {
            Elements elements = element.select("div>ul>li");
            String asin = "";

            for (Element e : elements) {
                if (e.text().contains("ASIN: ")) {
                    asin = e.text().substring(6);
                    String html = HttpUtils.getHtmlGet("https://www.amazon.cn/gp/product-description/ajaxGetProuductDescription.html?ref_=dp_apl_pc_loaddesc&merchantId=A1AJ19PSB66TGU&deviceType=web&asin=" + asin);
                    if (StringUtils.isNotEmpty(html)) {
                        Document userInfoDocument = Jsoup.parse(html);

                        Elements es = userInfoDocument.getElementsByClass("s-content");
                        for (Element one : es) {
                            String text = one.text();
                            if (text.contains("作者简介")) {
                                authorDescription = one.child(1).text();
                                break;
                            }
                        }
                    }
                    break;
                }
            }

        }

        bean.setAuthorInfo(authorDescription);
        }
        catch (Exception e){
            log.error("解析 作者描述 异常", e);
        }
    }


    @Override
    public String analyeUrl(String url) {
        return null;
    }

}
