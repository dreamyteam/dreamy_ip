package com.dreamy.crawler.sina;

import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/20.
 */
public class DataWeiBo {
    public static void main(String[] args) {
        String url="http://data.weibo.com/index/ajax/keywordzone?type=default&__rnd="+System.currentTimeMillis();;
        String value="DATA=usrmdinst_12;PHPSESSID=vfi7479rcq14bbj616tmtsq1e1;";
        String html = HttpUtils.getHtmlGetChangeCookie(url, value);
        System.out.println(html);
    }

    public static void main1(String[] args) {
        String title=HttpUtils.encodeUrl("大家");
        String url="http://data.weibo.com/index/ajax/hotword?flag=like&word="+title+"&_t=0&__rnd="+System.currentTimeMillis();

        String html = HttpUtils.getHtmlGet(url);
        String str= HttpUtils.decodeUnicode(html);
        Map<String,Object> map= JsonUtils.toMap(str);
        String code=(String) map.get("code");
        if(code.equals("100000")) {
            List<Map<String,String>> list=(List<Map<String,String>>)map.get("data");
            System.out.println(list.get(0).get("wid"));
        }

    }
}
