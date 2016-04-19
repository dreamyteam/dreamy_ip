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

/**
 * Created by wangyongxing on 16/4/6.
 */
@Component
public class JdCrawlerHandler extends AbstractCrawlerHandler {
    @Override
    public Integer getId() {
        return CrawlerSourceEnums.jd.getType();
    }

    @Override
    public BookInfo getByUrl(String url) {

        String html = HttpUtils.getHtmlGetBycharSet(url, "gbk");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Element author = document.getElementById("p-author");
                bean.setAuthor(author.text());
                Element image = document.getElementById("spec-n1").getElementsByTag("img").first();
                bean.setImage(image.attr("src"));
                bean.setTitle(image.attr("alt"));
            }


        }
        return null;

    }

    /**
     * 解析出版社 出版时间
     * @param bookInfo
     * @param document
     */
    private void pushTime(BookInfo bookInfo, Document document) {

        Elements types = document.select("ul.p-parameter-list>li");
        if (types != null && types.size() > 0) {
            bookInfo.setPress(types.get(0).attr("title"));
            bookInfo.setPushTime(types.get(7).attr("title"));
        }
    }

    /**
     * 编辑评论
     * @param bookInfo
     * @param document
     */
    private void comment(BookInfo bookInfo, Document document) {


        Element comment = document.getElementsByClass("book-detail-content").first();
        bookInfo.setComment(comment.text());
    }





    @Override
    public String analyeUrl(String url) {
        return null;
    }


}
