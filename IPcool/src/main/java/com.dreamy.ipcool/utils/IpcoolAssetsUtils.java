package com.dreamy.ipcool.utils;

import com.dreamy.service.AssetsService;
import com.dreamy.service.cache.RedisClientService;
import com.dreamy.service.iface.CommonService;
import com.dreamy.service.impl.CommonServiceImpl;
import com.dreamy.utils.CollectionUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/30
 * Time: 下午3:01
 */
@Component
public class IpcoolAssetsUtils extends AssetsService {
    private static String projectName = "ipcool";
    public static List __cssMap = new ArrayList<Map>();
    public static List __jsMap = new ArrayList<Map>();
    public static Boolean isDev = true;


    public static String getCssByKey(String key) {
        String sourceHtml = "";

        List<Map<String, Object>> cssMap = __cssMap;
        if (CollectionUtils.isNotEmpty(cssMap)) {
            for (Map<String, Object> map : cssMap) {
                if (key.equals(map.get("key"))) {
                    ArrayList keyValue = (ArrayList) map.get("value");
                    if (CollectionUtils.isNotEmpty(keyValue)) {
                        sourceHtml += "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + getAssetsHost(isDev) + "/" + projectName + keyValue.get(0) + "\">\r\n";
                    }
                    break;
                }
            }

        }

        return sourceHtml;
    }

    public static String getJsByKey(String key) {
        String sourceHtml = "";

        List<Map<String, Object>> jsMap = __jsMap;

        if (CollectionUtils.isNotEmpty(jsMap)) {
            for (Map<String, Object> map : jsMap) {
                if (key.equals(map.get("key"))) {

                    ArrayList keyValues = (ArrayList) map.get("value");
                    if (CollectionUtils.isNotEmpty(keyValues)) {
                        sourceHtml += "<script type=\"text/javascript\" src=\"" + getAssetsHost(isDev) + "/" + projectName + keyValues.get(0) + "\"></script>\r\n";
                    }
                    break;
                }
            }


        }

        return sourceHtml;
    }

    public static void getCssMap() {
        String resourceMapStr = getAssetsResourceMapJson(isDev, projectName);

        if (StringUtils.isNotEmpty(resourceMapStr)) {
            Map<String, Object> resourceMap = JsonUtils.toMap(resourceMapStr);
            if (CollectionUtils.isNotEmpty(resourceMap)) {
                __cssMap = (List<Map<String, Object>>) resourceMap.get("cssMapJson");
            }
        }
    }


    public static void getJsMap() {
        String resourceMapStr = getAssetsResourceMapJson(isDev, projectName);

        if (StringUtils.isNotEmpty(resourceMapStr)) {
            Map<String, Object> resourceMap = JsonUtils.toMap(resourceMapStr);
            if (CollectionUtils.isNotEmpty(resourceMap)) {
                __jsMap = (List<Map<String, Object>>) resourceMap.get("jsMapJson");
            }
        }
    }
}
