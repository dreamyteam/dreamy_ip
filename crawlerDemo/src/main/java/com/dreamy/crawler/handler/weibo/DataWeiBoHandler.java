package com.dreamy.crawler.handler.weibo;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/26.
 */
public class DataWeiBoHandler {
    public void crawler(String cookie, int bookId) {


    }


    public static void main(String[] args) {

        String value = "DATA=usrmdinst_12;PHPSESSID=vfi7479rcq14bbj616tmtsq1e1;";
        drawAreaJson(value);
    }

    /**
     * @param cookie
     */
    public static void drawAreaJson(String cookie) {
        String url = "http://data.weibo.com/index/ajax/getdefaultattributealldata?type=default&__rnd=" + System.currentTimeMillis();
        ;
        String value = "DATA=usrmdinst_17;PHPSESSID=fi9atbq7i0rjp2d7a1qg9jd0j2;";
        String json = HttpUtils.getHtmlGetChangeCookie(url, value);
        json = HttpUtils.decodeUnicode(json);
        System.out.println(json);
        BookIndexData bookIndexData = new BookIndexData();
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Object> map = JsonUtils.toMap(json);
            Map<String, Object> data = (Map<String, Object>) map.get("data");

            Map<String, Object> sex = (Map<String, Object>) data.get("sex");
            Map<String, Object> keys2 = (Map<String, Object>) sex.get("key2");


            Map<String, Object> age = (Map<String, Object>) data.get("age");
            Map<String, Object> ageKeys2 = (Map<String, Object>) age.get("key2");
            System.out.println(keys2.get("man"));
            System.out.println(keys2.get("woman"));
            Map<String, String> ages = (Map<String, String>) ageKeys2.get("0");
            Collection<String> list = ages.values();
            String[] array = (String[]) list.toArray(new String[list.size()]);
            bookIndexData.setAge(array);
            bookIndexData.setMale(keys2.get("man").toString());
            bookIndexData.setFemale(keys2.get("woman").toString());


        }


    }
}
