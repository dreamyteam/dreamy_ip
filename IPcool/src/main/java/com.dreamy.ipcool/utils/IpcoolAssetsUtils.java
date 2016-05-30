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

    @Autowired
    private RedisClientService redisClientService;

    @Autowired
    private CommonService commonService;


    public static String getCssByKey(String key) {
        String sourceHtml = "";

        List<Map<String, Object>> cssMap = __cssMap;
        CommonService commonService = new CommonServiceImpl();
        if (CollectionUtils.isNotEmpty(cssMap)) {
            for (Map<String, Object> map : cssMap) {
                if (key.equals(map.get("key"))) {
                    ArrayList keyValue = (ArrayList) map.get("key");
                    if (CollectionUtils.isNotEmpty(keyValue)) {
                        sourceHtml = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + getAssetsHost(commonService.isDev()) + "/" + projectName + keyValue.get(0) + "\">";
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
        CommonService commonService = new CommonServiceImpl();

        if (CollectionUtils.isNotEmpty(jsMap)) {
            for (Map<String, Object> map : jsMap) {
                if (key.equals(map.get("key"))) {

                    ArrayList keyValues = (ArrayList) map.get("value");
                    if (CollectionUtils.isNotEmpty(keyValues)) {
                        sourceHtml = "<script type=\"text/javascript\" src=\"" + getAssetsHost(commonService.isDev()) + "/" + projectName + keyValues.get(0) + "\"></scrip>";
                    }
                    break;
                }
            }


        }

        return sourceHtml;
    }

    public static void getCssMap() {
        CommonService commonService = new CommonServiceImpl();
        String resourceMapStr = getAssetsResourceMapJson(commonService.isDev(), projectName);

        if (StringUtils.isNotEmpty(resourceMapStr)) {
            Map<String, Object> resourceMap = JsonUtils.toMap(resourceMapStr);
            if (CollectionUtils.isNotEmpty(resourceMap)) {
                __cssMap = (List<Map<String, Object>>) resourceMap.get("cssMapJson");
            }
        }
    }


    public static void getJsMap() {
        CommonService commonService = new CommonServiceImpl();
        String resourceMapStr = getAssetsResourceMapJson(commonService.isDev(), projectName);

        if (StringUtils.isNotEmpty(resourceMapStr)) {
            Map<String, Object> resourceMap = JsonUtils.toMap(resourceMapStr);
            if (CollectionUtils.isNotEmpty(resourceMap)) {
                __jsMap = (List<Map<String, Object>>) resourceMap.get("jsMapJson");
            }
        }
    }
}
