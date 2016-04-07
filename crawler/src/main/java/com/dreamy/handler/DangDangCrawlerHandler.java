package com.dreamy.handler;

import com.dreamy.handler.AbstractCrawlerHandler;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.ConstUtil;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by wangyongxing on 16/4/6.
 */
public class DangDangCrawlerHandler extends AbstractCrawlerHandler {
    @Override
    public int getId() {
        return ConstUtil.CRAWLER_SOURCE_DD;
    }

    @Override
    public BookInfo getByUrl(String url) {

        String html = HttpUtils.getHtmlGet(url, "http://www.qidian.com/Book/3620214.aspx");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                Elements images = document.select("div.pic_box >a>img");
                if (images != null && images.size() > 0) {

                    Element image = images.first();
                    if (image != null) {
                        System.out.println(image.attr("src"));
                    }
                }

            }
            Elements images = document.select("div.book_info>div.title>h1");
            System.out.println(images.first().text());
            Elements images1 = document.select("div.book_info>div.title>span").first().getElementsByAttributeValue("itemprop","name");
            Elements images11 = document.getElementById("divBookInfo").getElementsByAttributeValue("itemprop","name");

            System.out.println(11);



        }
        return null;

    }



    @Override
    public String analyeUrl(String url) {
        return null;
    }


}
