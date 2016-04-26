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


    public BookIndexData getByUrl(String name,String area) throws UnsupportedEncodingException {
        BookIndexData data=new BookIndexData();
        String q = java.net.URLEncoder.encode(name, "UTF-8");
        area = java.net.URLEncoder.encode(area, "GBK");
        drawAreaJson(data,q);
        soMediaJson(data,name,q,area);
        soIndexJson(data,name,q,area);
        portrayalJson(data,q);
        overviewJson(data,q,area);
        return data;


    }


    /**
     * 地域分布
     * @param q
     */
    private void drawAreaJson(BookIndexData data,String q) {
        String url = "http://index.so.com/index.php?a=drawAreaJson&&t=30&q=" + q;
        String json = HttpUtils.getHtmlGet(url);
        json = decodeUnicode(json);
        Map<String, Object> map = JsonUtils.toMap(json);
        So so=JsonUtils.toObject(So.class,json);
        if(so.getStatus()==0){
            data.setAreaList(so.getData().getList());
        }


    }

    /**
     * 媒体关注度
     * @param name
     * @param q
     * @param area
     */
    public static void soMediaJson(BookIndexData data,String name,String q, String area) {
        String url = "http://index.so.com/index.php?a=soMediaJson&q=" + q;
        String json = HttpUtils.getHtmlGet(url);
        json = decodeUnicode(json);
        Map<String, Object> map = JsonUtils.toMap(json);
        Map<String, Object> map1 = (Map<String, Object>) map.get("data");
        Map<String, Object> map2 = (Map<String, Object>) map1.get("media");
        Map<String, Object> map3 = (Map<String, Object>) map1.get("period");
        String str = (String) map2.get(name);
        String arr[]=str.split("\\|");
        data.setMedia(arr);

    }


    public static void soIndexJson(BookIndexData data,String name,String q, String area) {
        String url = "http://index.so.com/index.php?a=soIndexJson&q=" + q + "&area" + area;
        String json = HttpUtils.getHtmlGet(url);
        json = decodeUnicode(json);
        Map<String, Object> map = JsonUtils.toMap(json);
        Map<String, Object> map1 = (Map<String, Object>) map.get("data");
        Map<String, Object> map2 = (Map<String, Object>) map1.get("index");
        Map<String, Object> map3 = (Map<String, Object>) map1.get("period");
        System.out.println(map3.get("from"));
        System.out.println(map3.get("to"));
        String str = (String) map2.get(name);
        String arr[]=str.split("\\|");
        data.setIndex(arr);


    }

    public static void portrayalJson(BookIndexData data,String q) {
        String url = "http://index.so.com/index.php?a=portrayalJson&t=30&q=" + q;
        String json = HttpUtils.getHtmlGet(url);
        json = decodeUnicode(json);
        So so=JsonUtils.toObject(So.class,json);
        data.setTags(so.getData().getTags());
        data.setAge(so.getData().getAge().split(","));
        data.setMale(so.getData().getMale());
        data.setFemale(so.getData().getFemale());

    }

    public static void overviewJson(BookIndexData data,String q, String area) {
        String url = "http://index.so.com/index.php?a=overviewJson&q=" + q + "&area" + area;
        String json = HttpUtils.getHtmlGet(url);
        json = decodeUnicode(json);
        json=json.replace("[","").replace("]","");
        So so=JsonUtils.toObject(So.class,json);
        data.setOverviewJson(so.getData().getData());

    }
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }


}
