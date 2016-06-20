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


/**
 * Created by wangyongxing on 16/4/18.
 * <p>
 * 关键词搜索
 */
@Component
public class KeyWordSoHandler {

    private static final Logger log = LoggerFactory.getLogger(KeyWordSoHandler.class);
    @Resource
    KeyWordService keyWordService;
    @Resource
    CrawlerService crawlerService;

    /**
     * 360 关键词搜索
     *
     * @param word
     * @param bookId
     */
    public void crawler(String word, Integer bookId) {
        word = HttpUtils.encodeUrl(word);
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
        String word = "不是每个故事都有结局+王豖";
        word = HttpUtils.encodeUrl(word);

        String url = "https://www.so.com/s?ie=utf-8&shb=1&src=home_so.com&q=" + word;
        String html = HttpUtils.getHtmlGet(url);

        System.out.println(html);
    }

}
