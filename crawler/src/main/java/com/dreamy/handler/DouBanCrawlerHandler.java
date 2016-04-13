package com.dreamy.handler;

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
public class DouBanCrawlerHandler extends AbstractCrawlerHandler {
    @Override
    public int getId() {
        return ConstUtil.CRAWLER_SOURCE_DD;
    }

    @Override
    public BookInfo getByUrl(String url) {

        String html = HttpUtils.getHtmlGetBycharSet(url, "utf-8");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if(document!=null)
            {
                BookInfo bean=new BookInfo();
            }





            }


        return null;

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
            bookInfo.setAuthor(content.get(0).text());
            bookInfo.setPress(content.get(1).text());
            bookInfo.setPushTime(content.get(2).text());

        }
    }

    private void getClickNum(BookInfo bookInfo, Document document) {
        Element content = document.getElementById("comm_num_down");
        if (content != null) {
            bookInfo.setClickNum(content.text());
        }
    }


    private void getType(BookInfo bookInfo, Document document) {
        Elements types = document.select("span.lie");
        StringBuffer infos = new StringBuffer();
        if (types != null && types.size() > 0) {
            for (Element element : types) {
                infos.append(element.text() + ",");

            }
            bookInfo.setType(infos.toString());
        }

    }


    @Override
    public String analyeUrl(String url) {
        return null;
    }


}
