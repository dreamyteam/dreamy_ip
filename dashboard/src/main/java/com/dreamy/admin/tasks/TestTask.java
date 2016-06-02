package com.dreamy.admin.tasks;

import com.dreamy.beans.Page;
import com.dreamy.domain.ipcool.BookView;
import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.BookTypeEnums;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.enums.RedisConstEnums;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.ipcool.BookViewService;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.utils.*;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/26
 * Time: 上午11:08
 */
@Component
public class TestTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

    @Autowired
    private BookViewService bookViewService;

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private RedisClientService redisClientService;

//    @Scheduled(fixedDelay = 190000)
    public void run() {
        LOGGER.info("start update rank job.." + TimeUtils.toString("yyyy-MM-dd HH:mm:ss", new Date()));

        int currentPage = 1;
        Page page = new Page();
        page.setPageSize(100);
        Boolean isLoop = true;
        String tt = HttpUtils.decodeUrl("%25E5%25A4%25B4%25E5%258F%2591%2520%25E4%25B8%259C%25E9%2587%258E%25E5%259C%25AD%25E5%2590%25BE");

        while (isLoop) {
            try {
                page.setCurrentPage(currentPage);
                List<BookView> bookViewList = bookViewService.getListByPageAndOrderAndType(page, "id desc", BookTypeEnums.chuban.getType());
                if (CollectionUtils.isNotEmpty(bookViewList)) {
                    for (BookView bookView : bookViewList) {
                        getSina(bookView.getName() + " " + bookView.getAuthor(), bookView.getBookId());
                    }
                    currentPage++;
                } else {
                    isLoop = false;
                }

            } catch (Exception e) {
                LOGGER.error("update index jod error ", e);
                break;
            }
        }
    }

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
                keyWord.indexNum(Integer.valueOf(result.replace(",", "")));
                keyWord.source(KeyWordEnums.weibo.getType());
                keyWordService.saveOrUpdate(keyWord);
            } else {
                if (check(responseText)) {
                    LOGGER.info(bookId + " 微博搜索结果 " + responseText);
                }
            }
        } catch (Exception e) {
            LOGGER.error("微博搜索结果 失败 ", e);
        }

    }

    private String getCookies() throws Exception {
        String value = redisClientService.get(RedisConstEnums.weibo.getCacheKey());
        int num = 1;
        if (StringUtils.isNotEmpty(value)) {
            num = Integer.valueOf(value);
        }
        String name = RedisConstEnums.weiboCookieName.getCacheKey() + NumberUtils.randomInt(1, num);
        String cookie = redisClientService.get(name);
        if (StringUtils.isEmpty(cookie)) {
            init();
            cookie = redisClientService.get(RedisConstEnums.weiboCookieName.getCacheKey() + 1);
            if (StringUtils.isEmpty(cookie)) {
                LoginSina ls = new LoginSina(CrawSina.weiboUsername, CrawSina.weiboPassword);
                ls.dologinSina();
                redisClientService.set(RedisConstEnums.weiboCookieName.getCacheKey() + 1, CrawSina.Cookie);
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
                redisClientService.set(RedisConstEnums.weiboCookieName.getCacheKey() + i, CrawSina.Cookie, 3600);
                i++;
            }
        }
        redisClientService.set(RedisConstEnums.weibo.getCacheKey(), i);
    }


}
