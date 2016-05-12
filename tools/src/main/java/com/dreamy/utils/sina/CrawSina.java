package com.dreamy.utils.sina;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangyongxing on 16/4/28.
 */


public class CrawSina {

    public static String CookieTC = "";
    public static String Cookie = "";
    public static String ticket = "";
    public static String loginUrl = "http%3A%2F%2Fweibo.com%2Fajaxlogin.php%3Fframelogin%3D1%26callback%3Dparent.sinaSSOController.feedBackUrlCallBack%26sudaref%3Dweibo.com";
    public static boolean enableProxy = false;
    public static String weiboUsername = "376427853@qq.com";
    public static String weiboPassword = "alan19880418";
    public static int hourbefore = 0;

    public static final Map<String, String> SINA_USERS = new LinkedHashMap<String, String>();

    static {
        SINA_USERS.put("hanshinan92@gmail.com", "http://3w.mailcn");
        SINA_USERS.put("376427853@qq.com", "alan19880418");
        SINA_USERS.put("18768129443", "luckysheep33");



    }

}
