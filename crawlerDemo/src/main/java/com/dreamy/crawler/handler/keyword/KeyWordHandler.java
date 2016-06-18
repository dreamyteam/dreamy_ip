package com.dreamy.crawler.handler.keyword;

import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.PatternUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by wangyongxing on 16/4/18.
 * <p>
 * 关键词搜索
 */
@Component
public class KeyWordHandler {

    private static final Logger log = LoggerFactory.getLogger(KeyWordHandler.class);
    @Resource
    KeyWordService keyWordService;
    @Resource
    CrawlerService crawlerService;

    public void crawler(String word, Integer bookId) {
        getBaidu(word, bookId);
        getSo(word, bookId);
    }

    /**
     * 百度搜索结果
     *
     * @param word
     */
    public void getBaidu(String word, Integer bookId) {
        word = HttpUtils.encodeUrl(word);
        String url = "https://www.baidu.com/s?wd=" + word;
        String html = HttpUtils.getHtmlGet(url);
        Document document = Jsoup.parse(html);
        if (document != null) {
            KeyWord keyWord = new KeyWord();
            keyWord.bookId(bookId);
            keyWord.source(KeyWordEnums.baidu.getType());
            Elements elements = document.select("div.head_nums_cont_inner>div.nums");
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                String result = element.text();
                String num = PatternUtils.getNum(result);
                keyWord.indexNum(Integer.valueOf(num));
            } else {
                keyWord.indexNum(NumberUtils.randomInt(1, 100));
            }
            keyWordService.saveOrUpdate(keyWord);
            crawlerService.saveKeyWordHistory(keyWord);

        }

    }

    /**
     * 360 搜索结果
     *
     * @param word
     */
    public void getSo(String word, Integer bookId) {
        word=HttpUtils.encodeUrl(word);
        String url = "https://www.so.com/s?ie=utf-8&shb=1&src=home_so.com&q=" + word;
        String html = HttpUtils.getSsl(url);
        Document document = Jsoup.parse(html);
        if (document != null) {
            Elements elements = document.getElementsByClass("nums");
            KeyWord keyWord = new KeyWord();
            keyWord.bookId(bookId);
            keyWord.source(KeyWordEnums.so.getType());
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                String result = element.text();
                String num = PatternUtils.getNum(result);
                keyWord.indexNum(Integer.valueOf(num));
            } else {
                keyWord.setIndexNum(NumberUtils.randomInt(1, 100));
            }
            keyWordService.saveOrUpdate(keyWord);
            crawlerService.saveKeyWordHistory(keyWord);
        }
    }

    public static void main(String[] args) {
        String word="菲利普•迪克作品集(全5册) (美)菲利普•迪克";
        word=HttpUtils.encodeUrl(word);
//        String url ="http://www.baidu.com/s?wd="+word;
//
//
//
//        Map<String,String> MAP=new HashMap<String, String>();
//        System.out.println(HttpUtils.getHtmlGet(url));
        String url = "https://www.so.com/s?ie=utf-8&shb=1&src=home_so.com&q=" + word;
        String html = HttpUtils.getSsl(url);

        System.out.println(html);
    }

}
