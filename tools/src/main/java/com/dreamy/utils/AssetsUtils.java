

package com.dreamy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AssetsUtils {
    public static final String ASSETS_HOST = "http://assets.ipcool.me";
    public static final String ASSETS_HOST_DEV = "http://dev.assets.ipcool.me";
    public static final String ASSETS_TYPE_CSS = "css";
    public static final String ASSETS_TYPE_JS = "js";
    public static Map<String, String> _cssResourceMap = new HashMap();
    public static Map<String, String> _jsResourceMap = new HashMap();

    public AssetsUtils() {
    }

    public static String getAssetsResourceMapJson(boolean isDev, String projectName) {
        StringBuilder url = new StringBuilder();
        url.append(getAssetsHost(isDev));
        url.append(projectName);
        url.append(System.currentTimeMillis());
        String json = HttpUtils.getHtmlGet(url.toString());

        return StringUtils.isNotEmpty(json) ? json : "";
    }


    public static String getAssetsHost(boolean isDev) {
        return isDev ? ASSETS_HOST : ASSETS_HOST_DEV;
    }

    public static void buildAssetsResourceMap(String json, boolean isDev, String prefix) {
        if (StringUtils.isNotEmpty(json)) {
            Map map = JsonUtils.toMap(json);
            if (CollectionUtils.isNotEmpty(map)) {
                Map cssResourceMap = buildResourceMap((List) map.get("cssMapJson"), isDev, "css", prefix);
                if (CollectionUtils.isNotEmpty(cssResourceMap)) {
                    _cssResourceMap.putAll(cssResourceMap);
                }

                Map jsResourceMap = buildResourceMap((List) map.get("jsMapJson"), isDev, "js", prefix);
                if (CollectionUtils.isNotEmpty(jsResourceMap)) {
                    _jsResourceMap.putAll(jsResourceMap);
                }
            }
        }

    }

    private static Map<String, String> buildResourceMap(List<Map<String, Object>> List, boolean isDev, String type, String prefix) {
        HashMap result = new HashMap();
        if (CollectionUtils.isNotEmpty(List)) {
            String valueKey = isDev ? "exploded" : "packaged";
            Iterator i$ = List.iterator();

            while (i$.hasNext()) {
                Map item = (Map) i$.next();
                if (item != null && item.get("key") != null && item.get(valueKey) != null) {
                    Object object = item.get(valueKey);
                    Object values = new ArrayList();
                    if (object instanceof String) {
                        ((List) values).add((String) object);
                    } else {
                        values = (List) object;
                    }

                    result.put((StringUtils.isNotEmpty(prefix) ? prefix : "") + (String) item.get("key"), buildHtmlResourceMap((List) values, isDev, type));
                }
            }
        }

        return result;
    }

    private static String buildHtmlResourceMap(List<String> list, boolean isDev, String type) {
        StringBuilder html = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list) && StringUtils.isNotEmpty(type)) {
            String head = null;
            String foot = null;
            if (type.equals("css")) {
                head = "<link rel=\"stylesheet\" href=\"";
                foot = "\"/>";
            } else {
                head = "<script data-main=\"";
                foot = "\" src=\"http://c4.duotin.com/2014/podcast/bower_components/requirejs/require.js\"></script>";
            }

            Iterator i$ = list.iterator();

            while (i$.hasNext()) {
                String item = (String) i$.next();
                if (StringUtils.isNotEmpty(item)) {
                    html.append(head);
                    html.append(getAssetsHost(isDev));
                    html.append(item);
                    html.append(foot);
                    html.append("\r\n");
                }
            }
        }

        return html.toString();
    }

    public static String getJsResourceByKey(String key) {
        return StringUtils.isNotEmpty(key) ? (String) _jsResourceMap.get(key) : "";
    }

    public static String getCssResourceByKey(String key) {
        return StringUtils.isNotEmpty(key) ? (String) _cssResourceMap.get(key) : "";
    }
}
