package com.dreamy.crawler.handler.keyword;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.service.cache.CommonService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.UserAgentService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.sina.CrawSina;
import com.dreamy.utils.sina.LoginSina;
import com.dreamy.utils.sina.SinaHttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by wangyongxing on 16/4/18.
 * <p>
 * 关键词搜索
 */
@Component
public class KeyWordHandler {
    @Autowired
    protected UserAgentService userAgentService;

    private static final Logger log = LoggerFactory.getLogger(KeyWordHandler.class);

    @Resource
    private KeyWordService keyWordService;

    @Resource
    CommonService commonService;

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
            Elements elements = document.getElementsByClass("nums");
            if (elements != null && elements.size() > 0) {
                KeyWord keyWord = null;
                Element element = elements.first();
                String result = element.text();
                String num = PatternUtils.getNum(result);
                keyWord = new KeyWord();
                keyWord.bookId(bookId);
                keyWord.indexNum(Integer.valueOf(num));
                keyWord.source(KeyWordEnums.baidu.getType());
                keyWordService.saveOrUpdate(keyWord);
            } else {
                log.info(bookId + " 百度搜索结果 "+html);
            }

        }

    }

    /**
     * 360 搜索结果
     *
     * @param word
     */
    public void getSo(String word, Integer bookId) {

        String url = "https://www.so.com/s?ie=utf-8&shb=1&src=home_so.com&q="+word;
        String html = HttpUtils.getSsl(url);
        Document document = Jsoup.parse(html);
        if (document != null) {
            Elements elements = document.getElementsByClass("nums");
            KeyWord keyWord = null;
            if (elements != null && elements.size() > 0) {
                Element element = elements.first();
                String result = element.text();
                String num = PatternUtils.getNum(result);
                keyWord = new KeyWord();
                keyWord.bookId(bookId);
                keyWord.source(KeyWordEnums.so.getType());
                keyWord.indexNum(Integer.valueOf(num));
                keyWordService.saveOrUpdate(keyWord);
            } else {
                log.info(bookId + " 360 搜索结果 "+ html);
            }
        }
    }









}
