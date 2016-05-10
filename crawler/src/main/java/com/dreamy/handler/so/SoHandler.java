package com.dreamy.handler.so;

import com.dreamy.mogodb.beans.BookIndexData;
import com.dreamy.mogodb.beans.Comment;
import com.dreamy.mogodb.beans.So;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wangyongxing on 16/4/18.
 */
@Component
public class SoHandler {

    private static final Logger log = LoggerFactory.getLogger(SoHandler.class);

    public BookIndexData getByUrl(String name, String area) throws UnsupportedEncodingException {

        BookIndexData data = null;
        try {
            name = name.replace(" ", "");
            String q = java.net.URLEncoder.encode(name, "UTF-8");
            area = java.net.URLEncoder.encode(area, "GBK");
            data = new BookIndexData();
            //check(q);
            drawAreaJson(data, q);
            soMediaJson(data, name, q, area);
            soIndexJson(data, name, q, area);
            portrayalJson(data, q);
            overviewJson(data, q, area);
        } catch (Exception e) {
           log.error("book " +name+"360 指数抓取失败 ",e);
        } finally {
            return data;
        }


    }

    private void check(String q) {
        String url =  "http://index.so.com/index.php?a=indexQuery&q="+q;
        String json = HttpUtils.getHtmlGet(url);
        json =HttpUtils.decodeUnicode(json);
        if (StringUtils.isNotEmpty(json)) {
            Map<String, Object> map = JsonUtils.toMap(json);
            Map<String, Object> map1 = (Map<String, Object>) map.get("data");
        }


    }



    /**
     * 地域分布
     *
     * @param q
     */
    private void drawAreaJson(BookIndexData data, String q) {
        String url = "http://index.so.com/index.php?a=drawAreaJson&&t=30&q=" + q;
        String json = HttpUtils.getHtmlGet(url);
        json = HttpUtils.decodeUnicode(json);
        if (StringUtils.isNotEmpty(json)) {
            So so = JsonUtils.toObject(So.class, json);
            if (so != null && so.getStatus() == 0) {
                data.setAreaList(so.getData().getList());
            }
        }


    }

    /**
     * 媒体关注度
     *
     * @param name
     * @param q
     * @param area
     */
    public static void soMediaJson(BookIndexData data, String name, String q, String area) {
        try {
            String url = "http://index.so.com/index.php?a=soMediaJson&q=" + q;
            String json = HttpUtils.getHtmlGet(url);
            json = HttpUtils.decodeUnicode(json);
            Map<String, Object> map = JsonUtils.toMap(json);
            int status = (Integer) map.get("status");
            if (status == 0) {
                Map<String, Object> map1 = (Map<String, Object>) map.get("data");
                Map<String, Object> map2 = (Map<String, Object>) map1.get("media");
                Map<String, Object> map3 = (Map<String, Object>) map1.get("period");
                String str = (String) map2.get(name);
                String arr[] = str.split("\\|");
                data.setMedia(arr);
            }
        } catch (Exception e) {
            log.error("book " +name+"360 指数抓取失败  媒体关注度",e);
        }

    }


    public static void soIndexJson(BookIndexData data, String name, String q, String area) {
        try {
            String url = "http://index.so.com/index.php?a=soIndexJson&q=" + q + "&area" + area;
            String json = HttpUtils.getHtmlGet(url);
            json = HttpUtils.decodeUnicode(json);
            Map<String, Object> map = JsonUtils.toMap(json);
            Map<String, Object> map1 = (Map<String, Object>) map.get("data");
            Map<String, Object> map2 = (Map<String, Object>) map1.get("index");
            Map<String, Object> map3 = (Map<String, Object>) map1.get("period");
            data.setLastDate(map3.get("to")+"");
//            System.out.println(map3.get("from"));
//            System.out.println(map3.get("to"));
            String str = (String) map2.get(name);
            String arr[] = str.split("\\|");
            data.setIndex(arr);
        } catch (Exception e) {
            log.error("book " +name+"360 指数抓取失败  soIndexJson",e);
        }


    }

    public static void portrayalJson(BookIndexData data, String word) {
        try {
            String url = "http://index.so.com/index.php?a=portrayalJson&t=30&q=" + word;
            String json = HttpUtils.getHtmlGet(url);
            json = HttpUtils.decodeUnicode(json);
            if (StringUtils.isNotEmpty(json)) {
                So so = JsonUtils.toObject(So.class, json);
                if (so != null) {
                    data.setTags(so.getData().getTags());
                    data.setAge(so.getData().getAge().split(","));
                    data.setMale(so.getData().getMale());
                    data.setFemale(so.getData().getFemale());
                }
            }
        } catch (Exception e) {
            log.error("book " +word+"360 指数抓取失败  portrayalJson",e);
        }

    }

    public static void overviewJson(BookIndexData data, String q, String area) {
        try {
            String url = "http://index.so.com/index.php?a=overviewJson&q=" + q + "&area" + area;
            String json = HttpUtils.getHtmlGet(url);
            json =HttpUtils.decodeUnicode(json);
            if (StringUtils.isNotEmpty(json)) {
                json = json.replace("[", "").replace("]", "");
                So so = JsonUtils.toObject(So.class, json);
                if (so != null) {
                    data.setOverviewJson(so.getData().getData());
                }
            }
        } catch (Exception e) {
            log.error("book " +q+"360 指数抓取失败  portrayalJson",e);
        }

    }


}
