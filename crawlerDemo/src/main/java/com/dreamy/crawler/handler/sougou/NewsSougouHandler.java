package com.dreamy.crawler.handler.sougou;

import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.domain.ipcool.NewsMedia;
import com.dreamy.service.iface.ipcool.NewsMediaService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wangyongxing on 16/5/6.
 */
@Component
public class NewsSougouHandler {
    private static final Logger log = LoggerFactory.getLogger(NewsSougouHandler.class);


    @Resource
    NewsMediaService newsMediaService;
    @Resource
    CrawlerService crawlerService;


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
                    if (hrefs.size() > 4) {
                        //newsMediaService.delByBookId(bookId);
                        for (Element elements1 : hrefs) {
                            url = "http://news.sogou.com/news" + elements1.attr("href");
                            get(url, i, bookId);
                            i++;
                        }
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
                NewsMedia newsMedia = new NewsMedia();
                newsMedia.bookId(bookId);
                newsMedia.type(1);
                newsMedia.source(source);
                if (elements != null && elements.size() > 0) {
                    Element element = elements.first();
                    PatternUtils.getNum(element.text());
                    newsMedia.num(Integer.valueOf(PatternUtils.getNum(element.text())));
                } else {
                    newsMedia.num(NumberUtils.randomInt(1, 20));
                }
                newsMediaService.saveOrUpdate(newsMedia);
                crawlerService.saveNewsMediaHistory(newsMedia);
            }
        }
    }

//    public void crawlerBak(String word, int bookId) {
//
//        OOSpider ooSpider = OOSpider.create(Site.me().setTimeOut(6000), NewsSougou.class);
//        word = word.replace(" ", "");
//        String url = "http://news.sogou.com/news?query=" + word;
//        NewsSougou sougou = ooSpider.<NewsSougou>get(url);
//        if (sougou != null) {
//            List<String> list = sougou.getUrls();
//            ooSpider.close();
//            if (CollectionUtils.isNotEmpty(list)) {
//                int size = list.size();
//                newsMediaService.delByBookId(bookId);
//                for (int i = 0; i < size; i++) {
//                    get(list.get(i), i, bookId);
//                }
//            }
//        }
//
//    }


//    public void getBak(String url, int source, int bookId) {
//        if (StringUtils.isNotEmpty(url)) {
//            OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), NewsSougou.class);
//            NewsSougou sougou = ooSpider.<NewsSougou>get(url);
//            if (sougou != null) {
//                NewsMedia newsMedia = new NewsMedia();
//                newsMedia.bookId(bookId);
//                newsMedia.type(1);
//                newsMedia.source(source + 1);
//                newsMedia.num(Integer.valueOf(PatternUtils.getNum(sougou.getNum())));
//                newsMediaService.save(newsMedia);
//            }
//            ooSpider.close();
//        }
//    }


}
