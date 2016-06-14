package com.dreamy.crawler.handler.weibo;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.SoArea;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/5/26.
 */
@Component
public class DataWeiBoHandler {

    public BookIndexData crawler(String cookie) {
        BookIndexData bookIndexData = new BookIndexData();
        getAge(bookIndexData, cookie);
        drawAreaJson(bookIndexData, cookie);
        return bookIndexData;
    }


    /**
     * @param cookie
     */
    public static void getAge(BookIndexData bookIndexData, String cookie) {
        String url = "http://data.weibo.com/index/ajax/getdefaultattributealldata?type=default&__rnd=" + System.currentTimeMillis();

        String json = HttpUtils.getHtmlGetChangeCookie(url, cookie);
        json = HttpUtils.decodeUnicode(json);
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Object> map = JsonUtils.toMap(json);
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            Map<String, Object> sex = (Map<String, Object>) data.get("sex");
            Map<String, Object> keys2 = (Map<String, Object>) sex.get("key2");
            Map<String, Object> age = (Map<String, Object>) data.get("age");
            Map<String, Object> ageKeys2 = (Map<String, Object>) age.get("key2");
            Map<String, String> ages = (Map<String, String>) ageKeys2.get("0");
            Collection<String> list = ages.values();
            String[] array = (String[]) list.toArray(new String[list.size()]);
            bookIndexData.setAge(array);
            bookIndexData.setMale(keys2.get("man").toString());
            bookIndexData.setFemale(keys2.get("woman").toString());

        }


    }

    public static void drawAreaJson(BookIndexData bookIndexData, String cookie) {
        String url = "http://data.weibo.com/index/ajax/keywordzone?type=default&__rnd=" + System.currentTimeMillis();
        String json = HttpUtils.getHtmlGetChangeCookie(url, cookie);
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Object> map = JsonUtils.toMap(json);
            Map<String, Object> map1 = (Map<String, Object>) map.get("zone");
            List<SoArea> list = new ArrayList<SoArea>();
            for (Map.Entry<String, Object> entry : map1.entrySet()) {
                Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
                SoArea soArea = new SoArea();
                soArea.setRank(Integer.valueOf(map2.get("index").toString()));
                soArea.setProvince(entry.getKey());
                String value = (String) map2.get("value");
                if (StringUtils.isNotEmpty(value)) {
                    value = value.replace("%", "");
                    soArea.setPerctent(Double.valueOf(value));
                }
                list.add(soArea);
            }
            bookIndexData.setAreaList(list);
        }


    }

    public static void chartDataJson(BookIndexData bookIndexData, String cookie) {
        String url = "http://data.weibo.com/index/ajax/getchartdata?month=default&__rnd=" + System.currentTimeMillis();
        String json = HttpUtils.getHtmlGetChangeCookie(url, cookie);
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Object> map = JsonUtils.toMap(json);
            List<Map<String, Object>> list=(List<Map<String, Object>> )map.get("data");
            Map<String, Object> m=list.get(0);
            List<Map<String, Object>> list1=(List<Map<String, Object>>)m.get("yd");
            System.out.println(m.get("yd"));
            System.out.println(map.get("data"));
        }


    }


    public static void main(String[] args) {

        String value = "PHPSESSID=dht3u8vtfpk1revoiv33ibcel1;WEB3_PHP-FPM_GD=99b485668fec0156ac6854bef933c8cf;";
        BookIndexData bookIndexData = new BookIndexData();
//        getAge(bookIndexData, value);
//        drawAreaJson(bookIndexData, value);
        chartDataJson(bookIndexData, value);
    }
}
