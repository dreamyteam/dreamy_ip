package com.dreamy.crawler.handler.keyword;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.enums.RedisConstEnums;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.UserAgentService;
import com.dreamy.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by wangyongxing on 16/4/18.
 * <p>
 * 关键词搜索
 */
@Component
public class KeyWordWeiXinHandler {
    @Autowired
    protected UserAgentService userAgentService;

    private static final Logger log = LoggerFactory.getLogger(KeyWordWeiXinHandler.class);

    @Resource
    private KeyWordService keyWordService;

    @Autowired
    HashOperations<String, String, String> hashOperationsString;


    public void crawler(String word, Integer bookId) {
        getWeiXin(word, bookId);


    }

    /**
     * 微信搜索结果
     *
     * @param name
     */
    public void getWeiXin(String name, Integer bookId) {
        name = HttpUtils.encodeUrl(name);
        String url = "http://weixin.sogou.com/weixin?type=2&ie=utf8&query=" + name;
        String key = RedisConstEnums.sougouweixin.getCacheKey();
        Set<String> set = hashOperationsString.keys(RedisConstEnums.sougouweixin.getCacheKey());
        int num = 1;
        String filed = "";
        String cookie = "";
        if (CollectionUtils.isNotEmpty(set)) {
            num = set.size();
            List<String> list = new ArrayList(set);
            filed = list.get(NumberUtils.randomInt(1, num-1));
            cookie = hashOperationsString.get(key, filed);
        }
        crawleringByProxy(url, cookie, bookId, filed);


    }

    public void crawleringByProxy(String url, String value, Integer bookId, String filed) {
        String html = HttpUtils.getHtmlGetChangeCookie(url, value);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                KeyWord keyWord = new KeyWord();
                keyWord.bookId(bookId);
                keyWord.source(KeyWordEnums.weixin.getType());
                Element element = document.getElementById("scd_num");
                if (element != null) {
                    String result = element.text();
                    String num = PatternUtils.getNum(result);
                    keyWord.indexNum(Integer.valueOf(num));

                } else {
                    Elements elements = document.select("div.content-box");
                    if (elements != null && elements.size() > 0) {
                        element = elements.first();
                        keyWord.indexNum(NumberUtils.randomInt(1, 10));
                        hashOperationsString.delete(RedisConstEnums.sougouweixin.getCacheKey(), filed);
                        log.error(" weixin.sogou.com  crawler book " + bookId + " error " + element.text());
                    } else {
                        keyWord.indexNum(NumberUtils.randomInt(1, 10));
                    }
                }
                keyWordService.saveOrUpdate(keyWord);
            }

        }


    }
}
