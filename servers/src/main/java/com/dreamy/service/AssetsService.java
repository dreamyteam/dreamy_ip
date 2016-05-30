

package com.dreamy.service;

import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetsService {
    public static String ASSETS_HOST = "http://assets.ipcool.me";
    public static String ASSETS_HOST_DEV = "http://dev.assets.ipcool.me";


    public AssetsService() {
    }

    public static String getAssetsResourceMapJson(boolean isDev, String projectName) {
        String url = getAssetsHost(isDev) + "/" + projectName + "/resource_map.json";
        String json = HttpUtils.getHtmlGet(url);

        return StringUtils.isNotEmpty(json) ? json : "";
    }


    public static String getAssetsHost(boolean isDev) {
        return isDev ? ASSETS_HOST_DEV : ASSETS_HOST;
    }

}
