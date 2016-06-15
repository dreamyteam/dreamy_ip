//package com.dreamy.crawler;
//
//import com.dreamy.mogodb.beans.So;
//import com.dreamy.utils.HttpUtils;
//import com.dreamy.utils.JsonUtils;
//
//import java.io.UnsupportedEncodingException;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by wangyongxing on 16/4/18.
// */
//public class SoMain {
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String name = "少有人走的路";
//        String area = "全国";
//        String q = java.net.URLEncoder.encode(name, "UTF-8");
//        area = java.net.URLEncoder.encode(area, "GBK");
//        drawAreaJson(q);
//        soMediaJson(name,q, area);
//        soIndexJson(name,q, area);
//        portrayalJson(q);
//        overviewJson(q, area);
//
//
//    }
//
//    /**
//     * 地域分布
//     * @param q
//     */
//    public static void drawAreaJson(String q) {
//        String url = "http://index.so.com/index.php?a=drawAreaJson&&t=30&q=" + q;
//        String json = HttpUtils.getHtmlGet(url);
//        json = decodeUnicode(json);
//        System.out.println(json);
//        Map<String, Object> map = JsonUtils.toMap(json);
//        System.out.println(map.get("data"));
//        So so=JsonUtils.toObject(So.class,json);
//
//        System.out.println(111);
//
//    }
//
//    /**
//     * 媒体关注度
//     * @param name
//     * @param q
//     * @param area
//     */
//    public static void soMediaJson(String name,String q, String area) {
//        String url = "http://index.so.com/index.php?a=soMediaJson&q=" + q;
//        String json = HttpUtils.getHtmlGet(url);
//        json = decodeUnicode(json);
//        System.out.println(json);
//        Map<String, Object> map = JsonUtils.toMap(json);
//        System.out.println(map.get("data"));
//        Map<String, Object> map1 = (Map<String, Object>) map.get("data");
//        Map<String, Object> map2 = (Map<String, Object>) map1.get("media");
//        Map<String, Object> map3 = (Map<String, Object>) map1.get("period");
//        System.out.println(map3.get("from"));
//        System.out.println(map3.get("to"));
//        String str = (String) map2.get(name);
//        String arr[]=str.split("\\|");
//        System.out.println(str.split("\\|").toString());
//        System.out.println();
//
//    }
//
//
//    public static void soIndexJson(String name,String q, String area) {
//        String url = "http://index.so.com/index.php?a=soIndexJson&q=" + q + "&area" + area;
//        String json = HttpUtils.getHtmlGet(url);
//        json = decodeUnicode(json);
//        System.out.println(json);
//        Map<String, Object> map = JsonUtils.toMap(json);
//        System.out.println(map.get("data"));
//        Map<String, Object> map1 = (Map<String, Object>) map.get("data");
//        Map<String, Object> map2 = (Map<String, Object>) map1.get("index");
//        Map<String, Object> map3 = (Map<String, Object>) map1.get("period");
//        System.out.println(map3.get("from"));
//        System.out.println(map3.get("to"));
//        String str = (String) map2.get(name);
//        String arr[]=str.split("\\|");
//
//
//
//
//
//    }
//
//    public static void portrayalJson(String q) {
//        String url = "http://index.so.com/index.php?a=portrayalJson&t=30&q=" + q;
//        String json = HttpUtils.getHtmlGet(url);
//        json = decodeUnicode(json);
//        System.out.println(json);
//        So so=JsonUtils.toObject(So.class,json);
//        System.out.println(111);
//        Map<String, Object> map1 = JsonUtils.toMap(json);
//
//        System.out.println(map1.get("data"));
//
//
//    }
//
//    public static void overviewJson(String q, String area) {
//        String url = "http://index.so.com/index.php?a=overviewJson&q=" + q + "&area" + area;
//        String json = HttpUtils.getHtmlGet(url);
//        json = decodeUnicode(json);
//        json=json.replace("[","").replace("]","");
//        System.out.println(json);
//        So so=JsonUtils.toObject(So.class,json);
//
//        System.out.println(111);
//
//
//    }
//
//
//
//    public static void main11(String[] args) {
//
//        long time = System.currentTimeMillis();
//        String url = "http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd=" + time;
//        String json = HttpUtils.getHtmlGet(url, "utf-8", "", 0, "INAGLOBAL=2667795442976.0576.1459828210974; SUB=_2AkMgTr-Mf8NhqwJRmPwdz2LiZIp_ywDEiebDAHzsJxJjHn4S7T9lqCQ1L4QiewKNHa5VdZdAMCBn__4U2A..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55z29P9D9WFLaLyNPNYoIOERKsv2sJjS; DATA=usrmdinst_15; _s_tentry=-; Apache=1826308714225.8882.1460950460770; ULV=1460950460783:3:3:1:1826308714225.8882.1460950460770:1460621842520; WBStore=97e433e7cc20168d|undefined; PHPSESSID=uv4uc0717fosng4us86fk53gs1", "", "", "");
//        System.out.println(json);
//    }
//
//    public static String unicode2String(String unicode) {
//
//        StringBuffer string = new StringBuffer();
//
//        String[] hex = unicode.split("\\\\u");
//
//        for (int i = 1; i < hex.length; i++) {
//
//            // 转换出每一个代码点
//            int data = Integer.parseInt(hex[i], 16);
//
//            // 追加成string
//            string.append((char) data);
//        }
//
//        return string.toString();
//    }
//
//    public static void main1(String[] args) throws UnsupportedEncodingException {
//        String url = "https://www.amazon.cn/菊与刀-本尼迪克特/dp/B005HSLJWS/ref=sr_1_1?ie=UTF8&qid=1461037964&sr=8-1&keywords=菊与刀";
//
//        url = toUtf8String(url);
//        System.out.println(url);
//    }
//
//    public static String toUtf8String(String s) {
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c >= 0 && c <= 255) {
//                sb.append(c);
//            } else {
//                byte[] b;
//                try {
//                    b = String.valueOf(c).getBytes("utf-8");
//                } catch (Exception ex) {
//                    System.out.println(ex);
//                    b = new byte[0];
//                }
//                for (int j = 0; j < b.length; j++) {
//                    int k = b[j];
//                    if (k < 0)
//                        k += 256;
//                    sb.append("%" + Integer.toHexString(k).toUpperCase());
//                }
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String decodeUnicode(String theString) {
//        char aChar;
//        int len = theString.length();
//        StringBuffer outBuffer = new StringBuffer(len);
//        for (int x = 0; x < len; ) {
//            aChar = theString.charAt(x++);
//            if (aChar == '\\') {
//                aChar = theString.charAt(x++);
//                if (aChar == 'u') {
//                    // Read the xxxx
//                    int value = 0;
//                    for (int i = 0; i < 4; i++) {
//                        aChar = theString.charAt(x++);
//                        switch (aChar) {
//                            case '0':
//                            case '1':
//                            case '2':
//                            case '3':
//                            case '4':
//                            case '5':
//                            case '6':
//                            case '7':
//                            case '8':
//                            case '9':
//                                value = (value << 4) + aChar - '0';
//                                break;
//                            case 'a':
//                            case 'b':
//                            case 'c':
//                            case 'd':
//                            case 'e':
//                            case 'f':
//                                value = (value << 4) + 10 + aChar - 'a';
//                                break;
//                            case 'A':
//                            case 'B':
//                            case 'C':
//                            case 'D':
//                            case 'E':
//                            case 'F':
//                                value = (value << 4) + 10 + aChar - 'A';
//                                break;
//                            default:
//                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
//                        }
//                    }
//                    outBuffer.append((char) value);
//                } else {
//                    if (aChar == 't')
//                        aChar = '\t';
//                    else if (aChar == 'r')
//                        aChar = '\r';
//                    else if (aChar == 'n')
//                        aChar = '\n';
//                    else if (aChar == 'f')
//                        aChar = '\f';
//                    outBuffer.append(aChar);
//                }
//            } else
//                outBuffer.append(aChar);
//        }
//        return outBuffer.toString();
//    }
//
//    public static String chinaToUnicode(String str) {
//        String result = "";
//        for (int i = 0; i < str.length(); i++) {
//            int chr1 = (char) str.charAt(i);
//            if (chr1 >= 19968 && chr1 <= 171941) {//汉字范围 \u4e00-\u9fa5 (中文)
//                result += "\\u" + Integer.toHexString(chr1);
//            } else {
//                result += str.charAt(i);
//            }
//        }
//        return result;
//    }
//}
