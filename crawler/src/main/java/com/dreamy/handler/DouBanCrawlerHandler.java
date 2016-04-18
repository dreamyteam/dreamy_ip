package com.dreamy.handler;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.ConstUtil;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
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
public class DouBanCrawlerHandler extends AbstractCrawlerHandler {
    @Override
    public Integer getId() {
        return ConstUtil.CRAWLER_SOURCE_DB;
    }

    @Override
    public BookInfo getByUrl(String url) {

        String html = HttpUtils.getHtmlGetBycharSet(url, "utf-8");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if(document!=null)
            {
                BookInfo bean=new BookInfo();
                getAuthor(bean,document);
                getClickNum(bean,document);
                getImage(bean,document);
                getAuthorInfo(bean,document);
                getTags(bean,document);
                getScore(bean,document);
                return bean;

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
        Elements elements=document.getElementsByAttributeValue("name","keywords");
        Element element=elements.first();
        String content=element.attr("content");
        if(StringUtils.isNotEmpty(content))
        {
            String arr[]=content.split(",");
            int size=arr.length;
            if(size>5)
            {
                bookInfo.setTitle(arr[0]);
                bookInfo.setAuthor(arr[1]);
                bookInfo.setPress(arr[2]);
                bookInfo.setPushTime(arr[3]);
            }

        }
    }





    /**
     * 解析图片
     * @param bookInfo
     * @param document
     */
    private void getImage(BookInfo bookInfo, Document document) {
        Elements elements = document.getElementById("mainpic").getElementsByTag("img");
        if(elements!=null&&elements.size()>0)
        {
            Element element=elements.first();
            bookInfo.setImage(element.attr("src"));
        }


    }

    /**
     * 解析 作者简介 内容简介
     * @param bookInfo
     * @param document
     */
    private void getAuthorInfo(BookInfo bookInfo, Document document) {
        Elements elements = document.select("div.intro");
        if(elements!=null&&elements.size()>0)
        {
            Element element=elements.get(0);
            bookInfo.setInfo(element.text());
            element=elements.get(1);
            bookInfo.setAuthorInfo(element.text());
        }


    }
    /**
     * 总评论
     *
     * @param bean
     * @param document
     */
    private void getClickNum(BookInfo bean, Document document) {
        Elements elements = document.getElementsByClass("rating_people");
        if(elements!=null&&elements.size()>0){
            Element element=elements.first();
            String str=element.text();
            bean.setClickNum(PatternUtils.getNum(str));
        }


    }

    /**
     * 解析标签
     * @param bookInfo
     * @param document
     */
    private void getTags(BookInfo bookInfo, Document document) {
        Elements elements = document.select("div.blank20>div.indent>span>a");
        StringBuffer tags = new StringBuffer();
        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                tags.append(element.text() + ",");

            }
            bookInfo.setTags(tags.toString());
        }

    }

    /**
     * 评分
     * @param bookInfo
     * @param document
     */
    private void getScore(BookInfo bookInfo, Document document) {
        Elements elements = document.select("div.rating_wrap>div.rating_self>strong");


        if (elements != null && elements.size() > 0) {
            Element element=elements.first();
            bookInfo.setScore(elements.text());
        }

    }






    @Override
    public String analyeUrl(String url) {
        return null;
    }


}
