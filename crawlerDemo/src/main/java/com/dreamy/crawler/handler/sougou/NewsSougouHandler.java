package com.dreamy.crawler.handler.sougou;

import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.service.iface.ipcool.NewsMediaService;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/5/6.
 */
@Component
public class NewsSougouHandler {
    private static final Logger log = LoggerFactory.getLogger(NewsSougouHandler.class);

    private static final Map<Integer, String> CRAWL_SOURCES = new LinkedHashMap<Integer, String>();

    static {
        CRAWL_SOURCES.put(1, "搜获");
        CRAWL_SOURCES.put(2, "腾讯");
        CRAWL_SOURCES.put(3, "新浪");
        CRAWL_SOURCES.put(4, "凤凰");
        CRAWL_SOURCES.put(5, "网易");

    }

    @Resource
    NewsMediaService newsMediaService;


    public void crawler(String word, int bookId) {
        word = HttpUtils.encodeUrl(word);
        String url = "http://news.sogou.com/news?query=" + word;
        String html = HttpUtils.getHtmlGet(url, "GBK");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Elements elements = document.getElementsByClass("filt-pop");
                if (elements != null && elements.size() > 1) {
                    Elements hrefs = elements.get(1).getElementsByTag("a");

                    int i = 1;
                    for (Element elements1 : hrefs) {
                        url=elements1.attr("href");
                        get(url, i, bookId);
                        i++;
                    }

                }
            }
        }


    }

    public void get(String url, int source, int bookId) {
        String html = HttpUtils.getHtmlGet(url, "GBK");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Elements elements = document.getElementsByClass("filt-result");
                if (elements != null && elements.size() > 0) {
                    Element element = elements.first();
                    PatternUtils.getNum(element.text());
                    NewsMedia newsMedia = new NewsMedia();
                    newsMedia.bookId(bookId);
                    newsMedia.type(1);
                    newsMedia.source(source);
                    newsMedia.num(Integer.valueOf(PatternUtils.getNum(element.text())));
                    newsMediaService.save(newsMedia);
                }
            }
        }
    }

    public void crawlerBak(String word, int bookId) {

        OOSpider ooSpider = OOSpider.create(Site.me().setTimeOut(6000), NewsSougou.class);
        word = word.replace(" ", "");
        String url = "http://news.sogou.com/news?query=" + word;
        NewsSougou sougou = ooSpider.<NewsSougou>get(url);
        if (sougou != null) {
            List<String> list = sougou.getUrls();
            ooSpider.close();
            if (CollectionUtils.isNotEmpty(list)) {
                int size = list.size();
                newsMediaService.delByBookId(bookId);
                for (int i = 0; i < size; i++) {
                    get(list.get(i), i, bookId);
                }
            }
        }

    }


    public void getBak(String url, int source, int bookId) {
        if (StringUtils.isNotEmpty(url)) {
            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), NewsSougou.class);
            NewsSougou sougou = ooSpider.<NewsSougou>get(url);
            if (sougou != null) {
                NewsMedia newsMedia = new NewsMedia();
                newsMedia.bookId(bookId);
                newsMedia.type(1);
                newsMedia.source(source + 1);
                newsMedia.num(Integer.valueOf(PatternUtils.getNum(sougou.getNum())));
                newsMediaService.save(newsMedia);
            }
            ooSpider.close();
        }
    }


}
