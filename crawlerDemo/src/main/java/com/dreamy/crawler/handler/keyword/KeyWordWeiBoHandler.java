package com.dreamy.crawler.handler.keyword;

import com.dreamy.crawler.service.CrawlerService;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.enums.RedisConstEnums;
import com.dreamy.service.cache.CacheCommonService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.UserAgentService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.NumberUtils;
import com.dreamy.utils.StringUtils;
import com.dreamy.utils.sina.CrawSina;
import com.dreamy.utils.sina.LoginSina;
import com.dreamy.utils.sina.SinaHttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by wangyongxing on 16/4/18.
 * <p>
 * W微博 关键词搜索
 */
@Component
public class KeyWordWeiBoHandler {
    @Autowired
    protected UserAgentService userAgentService;

    private static final Logger log = LoggerFactory.getLogger(KeyWordWeiBoHandler.class);

    @Resource
    private KeyWordService keyWordService;
    @Resource
    CacheCommonService commonService;

    @Resource
    CrawlerService crawlerService;

    public void crawler(String word, Integer bookId) {
        getSina(word, bookId);
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
            KeyWord keyWord = new KeyWord();
            keyWord.source(KeyWordEnums.weibo.getType());
            keyWord.bookId(bookId);
            if (StringUtils.isNotEmpty(result)) {
                keyWord.indexNum(Integer.valueOf(result.replace(",", "")));
            } else {
                if (check(responseText)) {
                    log.info(bookId + " 微博搜索结果 " + responseText);
                }
                keyWord.indexNum(NumberUtils.randomInt(1,10));
            }
            keyWordService.saveOrUpdate(keyWord);
            crawlerService.saveKeyWordHistory(keyWord);
        } catch (Exception e) {
            log.error("微博搜索结果 失败 ", e);
        }

    }

    private String getCookies() throws Exception {
        String value = (String) commonService.getCacheService().get(RedisConstEnums.weibo.getCacheKey());
        int num = 1;
        if (StringUtils.isNotEmpty(value)) {
            num = Integer.valueOf(value);
        }
        String name = RedisConstEnums.weiboCookieName.getCacheKey() + NumberUtils.randomInt(1, num);
        String cookie = (String) commonService.getCacheService().get(name);
        if (StringUtils.isEmpty(cookie)) {
            init();
            cookie = (String) commonService.getCacheService().get(RedisConstEnums.weiboCookieName.getCacheKey() + 1);
            if (StringUtils.isEmpty(cookie)) {
                LoginSina ls = new LoginSina(CrawSina.weiboUsername, CrawSina.weiboPassword);
                ls.dologinSina();
                commonService.getCacheService().put(RedisConstEnums.weiboCookieName.getCacheKey() + 1, CrawSina.Cookie);
                cookie = CrawSina.Cookie;
            }

        }
        return cookie;
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

    public static boolean check(String str) {
        boolean result = str.contains("我真滴不是机器人");
        return result;
    }

    public void init() {

        int i = 1;
        for (Map.Entry<String, String> entry : CrawSina.SINA_USERS.entrySet()) {
            LoginSina ls = new LoginSina(entry.getKey(), entry.getValue());
            ls.dologinSina();
            if (StringUtils.isNotEmpty(CrawSina.Cookie)) {
                commonService.getCacheService().set(RedisConstEnums.weiboCookieName.getCacheKey() + i, CrawSina.Cookie, 3600);
                i++;
            }
        }
        commonService.getCacheService().put(RedisConstEnums.weibo.getCacheKey(), i);
    }

}
