package com.dreamy.handler;

import com.dreamy.enums.CrawlerSourceEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Component
public class DangDangCrawlerHandler extends AbstractCrawlerHandler {

    private static final Logger log = LoggerFactory.getLogger(DangDangCrawlerHandler.class);


    @Override
    public Integer getId() {
        return CrawlerSourceEnums.dangdang.getType();
    }

    @Override
    public BookInfo getByUrl(String url) {
        String html = HttpUtils.getHtmlGet(url, "gbk");
        BookInfo bean = null;
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                bean = new BookInfo();
                info(bean, document);
                image(bean, document);
                authorInfo(bean, document);
                comment(bean, document);
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
     * 作品内容
     *
     * @param bean
     * @param document
     */
    private void info(BookInfo bean, Document document) {
        Element content = document.getElementById("content");
        if (content != null) {
            Elements contents = content.getElementsByTag("textarea");
            if (contents != null && contents.size() > 0) {
                Element element = contents.first();
                bean.setInfo(element.text());
            }
        }
    }

    /**
     * 图片
     *
     * @param bean
     * @param document
     */
    private void image(BookInfo bean, Document document) {
        Element image = document.getElementById("largePic");
        if (image != null) {
            bean.setImage(image.attr("src"));
        }
    }

    /**
     * 作者信息
     *
     * @param bean
     * @param document
     */
    private void authorInfo(BookInfo bean, Document document) {
        try {


            Element authorintro = document.getElementById("authorintro");
            if (authorintro != null) {
                Elements elements = authorintro.getElementsByTag("textarea");
                if (elements != null && elements.size() > 0) {
                    Element content = elements.first();
                    bean.setAuthorInfo(content.text());
                }
            }
        } catch (Exception e) {
            log.error("解析 作者信息 异常", e);
        }

    }

    /**
     * 编辑评论
     *
     * @param bean
     * @param document
     */
    private void comment(BookInfo bean, Document document) {
        String editorComment = "";
        try {
            Elements comments = document.getElementById("abstract").getElementsByTag("textarea");
            if (comments != null && comments.size() > 0) {
                Element content = comments.first();
                editorComment = content.text();
            }
        } catch (Exception e) {
            log.error("解析 编辑推荐 异常", e);
        } finally {
            bean.setEditorComment(editorComment);
        }

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
                bookInfo.setPushTime(PatternUtils.date(content.get(2).text()));
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

            String str = infos.toString();
            bookInfo.setCategories(str.substring(0, str.length() - 1));
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
            String result = HttpUtils.getHtmlGet(url, "gbk");
            System.out.println(result);
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
        try {
            Element element = document.getElementById("pid_span");
            if (element != null) {
                String product_id = element.attr("product_id");
                String url = "http://product.dangdang.com/comment/comment.php?product_id=" + product_id + "&datatype=1&page=1&filtertype=1&sysfilter=1";
                String result = HttpUtils.getHtmlGet(url, "gbk");
                if (StringUtils.isNotEmpty(result) && !result.equals("[]")) {
                    Map<String, Object> map1 = JsonUtils.toMap(result);
                    if (CollectionUtils.isNotEmpty(map1)) {
                        Map<String, Object> map2 = (Map<String, Object>) map1.get("rateInfo");
                        if (CollectionUtils.isNotEmpty(map1)) {
                            String core = map2.get("good_rate").toString();
                            bookInfo.setScore(core);
                        }
                    }
                }


            }
        } catch (Exception e) {
            log.error("解析 评分 异常", e);
        }

    }


    @Override
    public String analyeUrl(String url) {
        return null;
    }

}
