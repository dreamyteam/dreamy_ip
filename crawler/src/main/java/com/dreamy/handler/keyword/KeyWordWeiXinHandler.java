package com.dreamy.handler.keyword;

import com.dreamy.domain.ipcool.KeyWord;
import com.dreamy.enums.KeyWordEnums;
import com.dreamy.service.iface.ipcool.KeyWordService;
import com.dreamy.service.iface.mongo.UserAgentService;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


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
        KeyWord keyWord = null;
        String cookie = "SUIR=1462265639;SUID=CCB2C7736A20900A0000000057286727;";
        String url = "http://weixin.sogou.com/weixin?type=2&ie=utf8&query=" + name;
        String html = HttpUtils.getHtmlGetByProxy(url, null, 0, userAgentService.getOneByRandom().getUserAgent());
        Document document = Jsoup.parse(html);
        if (document != null) {
            Element element = document.getElementById("scd_num");
            if (element != null) {
                String result = element.text();
                String num = PatternUtils.getNum(result);
                keyWord = new KeyWord();
                keyWord.bookId(bookId);
                keyWord.source(KeyWordEnums.weixin.getType());
                keyWord.indexNum(Integer.valueOf(num));
                keyWordService.saveOrUpdate(keyWord);
            } else {
                log.info(bookId + " 360 微信文章搜索结果 " + html);
            }
        }

    }


}
