package com.dreamy.handler.jd;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.selenium.SeleniumDownloader;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

/**
 * Created by wangyongxing on 16/4/6.
 */
@Component
public class JdCrawlerBookHandler {


    public BookInfo getByISBN(String isbn) {
        String url = "http://search.jd.com/Search?keyword=" + isbn + "&enc=utf-8&pvid=x00em9oi.5otj5i";
        String html = HttpUtils.getHtmlGet(url);
        Document document = Jsoup.parse(html);
        Elements books = document.select("div.goods-list-v1>ul.gl-warp>li.gl-item>div.gl-i-wrap>div.p-img>a");
        if (books != null && books.size() > 0) {
            String crawlerUrl = books.get(0).attr("href");
            crawlerUrl="http:"+crawlerUrl;
            BookInfo bookInfo = crawler(crawlerUrl);
            return bookInfo;
        }

        return null;
    }


    public BookInfo crawler(String url) {
        url = url + "#comment";
        String html = seleniumDownloader(url);//HttpUtils.getHtmlGetBycharSet(url, "gbk");
        BookInfo bean = null;
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                bean = new BookInfo();
                bean.setUrl(url);
                author(bean, document);
                imageAndTitle(bean, document);

                infoAndAuthorInfo(bean, document);
                saleSort(bean, document);
                pushTime(bean, document);
                score(bean, document);
                commentNum(bean, document);

            }


        }
        return bean;

    }

    /**
     * 解析平台销售排名
     *
     * @param bean
     * @param document
     */
    private void saleSort(BookInfo bean, Document document) {
        Element element = document.getElementById("summary-order");
        if (element != null) {
            String sort = PatternUtils.getNum(element.text());
            bean.setSaleSort(sort);
        }

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
     * 平台评分
     *
     * @param bean
     * @param document
     */
    private void score(BookInfo bean, Document document) {
        Elements elements = document.getElementsByClass("rate");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            String score = PatternUtils.getNum(element.text());
            bean.setScore(score);
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

//            bookInfo.setPress(types.get(0).attr("title"));
//            if (types.size() >7) {
//                bookInfo.setPushTime(types.get(7).attr("title"));
//            }

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
            e.printStackTrace();
        } finally {
            seleniumDownloader.close();
            return html;
        }


    }


}
