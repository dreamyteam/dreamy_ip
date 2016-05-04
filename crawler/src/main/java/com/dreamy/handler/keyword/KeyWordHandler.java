package com.dreamy.handler.keyword;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.handler.keyword.sina.CrawSina;
import com.dreamy.handler.keyword.sina.LoginSina;
import com.dreamy.handler.keyword.sina.PreLoginResponseMessage;
import com.dreamy.handler.keyword.sina.SinaHttpUtils;
import com.dreamy.service.cache.CacheService;
import com.dreamy.service.cache.CommonService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.UserAgentService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
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
import java.util.HashMap;
import java.util.List;
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
        getSina(word, bookId);
        getWeiXin(word, bookId);


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
                keyWord.source(KeyWordEnums.baidu.getType());
                keyWord.bookId(bookId);
                keyWord.indexNum(Integer.valueOf(num));
                keyWordService.saveOrUpdate(keyWord);
            } else {
                log.info(bookId + " 百度搜索结果 ", html);
            }

        }

    }

    /**
     * 360 搜索结果
     *
     * @param name
     */
    public void getSo(String name, Integer bookId) {
        String url = "https://www.so.com/s?ie=utf-8&shb=1&src=home_so.com&q=" + name;
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
                log.info(bookId + " 360 搜索结果 ", html);
            }
        }
    }

    /**
     * 微信搜索结果
     *
     * @param name
     */
    public void getWeiXin(String name, Integer bookId) {
        name = HttpUtils.encodeUrl(name);
        String cookie = "SUIR=1462265639;SUID=CCB2C7736A20900A0000000057286727;";
        String url = "http://weixin.sogou.com/weixin?type=2&ie=utf8&query=" + name;
        String html = HttpUtils.getHtmlGetByProxy(url, null, 0, userAgentService.getOneByRandom().getUserAgent());
        Document document = Jsoup.parse(html);
        if (document != null) {
            Element element = document.getElementById("scd_num");
            if (element != null) {
                KeyWord keyWord = null;
                String result = element.text();
                String num = PatternUtils.getNum(result);
                keyWord = new KeyWord();
                keyWord.bookId(bookId);
                keyWord.source(KeyWordEnums.weixin.getType());
                keyWord.indexNum(Integer.valueOf(num));
                keyWordService.saveOrUpdate(keyWord);
            } else {
                log.info(bookId + " 360 微信搜索结果 ", html);
            }
        }

    }

    /**
     *  微博搜索结果
     *
     * @param name
     * @throws IOException
     */
    public void getSina(String name, Integer bookId) {
        try {
            name = HttpUtils.encodeUrl(name);
            HttpClient client = new DefaultHttpClient();
            String url = "http://s.weibo.com/weibo/" + name;
            HttpGet request = new HttpGet(url);
            request.setHeader("Cookie", getCookies());
            HttpResponse response = client.execute(request);
            String responseText = SinaHttpUtils.getStringFromResponse(response);
            client.getConnectionManager().shutdown();
            responseText = HttpUtils.decodeUnicode(responseText);
            String result = getResult(responseText);
            if (StringUtils.isNotEmpty(result)) {
                KeyWord keyWord = new KeyWord();
                keyWord.bookId(bookId);
                keyWord.source(KeyWordEnums.weibo.getType());
                keyWord.indexNum(Integer.valueOf(result.replace(",", "")));
                keyWordService.saveOrUpdate(keyWord);
            } else {
                log.info(bookId + " 微博搜索结果 ", responseText);
            }
        } catch (Exception e) {
            log.error("微博搜索结果 失败 ", e);
        }

    }

    private static int getRandom(int size) {
        Random random = new Random();
        int result = random.nextInt(size);
        return result;
    }

    private String getCookies() throws Exception {
        String name = "cookie" + getRandom(2);
        String cookie = (String) commonService.getCacheService().get(name);
        if (StringUtils.isNotEmpty(cookie)) {
            return cookie;
        } else {
            init();
//            LoginSina ls = new LoginSina(CrawSina.weiboUsername, CrawSina.weiboPassword);
//            ls.dologinSina();
//            commonService.getCacheService().put("cookie0", CrawSina.Cookie);
            cookie = (String) commonService.getCacheService().get("cookie1");
            if (StringUtils.isEmpty(cookie)) {
                LoginSina ls = new LoginSina(CrawSina.weiboUsername, CrawSina.weiboPassword);
                ls.dologinSina();
                commonService.getCacheService().put("cookie1", CrawSina.Cookie);
                cookie = CrawSina.Cookie;
            }
            return cookie;
        }

    }


    public static String getResult(String str) {
        String result = "";
        Pattern p = Pattern.compile("找到(.*?)条结果");
        Matcher m = p.matcher(str);
        while (m.find()) {
            result = m.group(1);
        }
        return result;

    }


    public void init() {

        int i = 1;
        for (Map.Entry<String, String> entry : CrawSina.SINA_USERS.entrySet()) {

            LoginSina ls = new LoginSina(entry.getKey(), entry.getValue());
            ls.dologinSina();
            System.out.println(CrawSina.Cookie);
            commonService.getCacheService().put("cookie" + i, CrawSina.Cookie);
            i++;

        }
    }
}
