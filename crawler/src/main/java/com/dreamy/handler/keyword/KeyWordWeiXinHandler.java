package com.dreamy.handler.keyword;

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
import java.util.Set;


/**
 * Created by wangyongxing on 16/4/18.
 * <p>
 * 关键词搜索
 */
@Component
public class KeyWordWeiXinHandler {
    private static final Logger log = LoggerFactory.getLogger(KeyWordWeiXinHandler.class);
    @Autowired
    protected UserAgentService userAgentService;

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
        int num = 1;
        String key = RedisConstEnums.sougouweixin.getCacheKey();
        Set<String> set = hashOperationsString.keys(RedisConstEnums.sougouweixin.getCacheKey());

        if (CollectionUtils.isNotEmpty(set)) {
            num = set.size();
        }
        String filed = RedisConstEnums.sougouweixinCookieName.getCacheKey() + NumberUtils.randomInt(1, num);
        String cookie = hashOperationsString.get(key, filed);
        crawleringByProxy(url, cookie, bookId, filed);


    }

    public boolean crawleringByProxy(String url, String value, Integer bookId, String filed) {
        //value="SNUID=CC381923525761791A52B38D53BBB0D6;SUID=9F6A4A70E518920A00000000573D4FAC;SUV=003823E673C1A1355703368E72E2B579";
        String html = HttpUtils.getHtmlGetChangeCookie(url, value);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Element element = document.getElementById("scd_num");
                if (element != null) {

                    String result = element.text();
                    String num = PatternUtils.getNum(result);
                    KeyWord keyWord = new KeyWord();
                    keyWord.bookId(bookId);
                    keyWord.source(KeyWordEnums.weixin.getType());
                    keyWord.indexNum(Integer.valueOf(num));
                    keyWordService.saveOrUpdate(keyWord);
                } else {
                    Elements elements = document.select("div.content-box");
                    if (elements != null && elements.size() > 0) {
                        element = elements.first();
                        hashOperationsString.delete(RedisConstEnums.sougouweixin.getCacheKey(), filed);
                        System.out.println(element.text());
                        log.error(" weixin.sogou.com  crawler book " + bookId + " error " + element.text());
                    }


                }
            }
            return true;
        }
        return false;
    }


}
