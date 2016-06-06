package com.crawler.test;

import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.rabbitmq.tools.json.JSONUtil;
import net.sf.json.util.JSONUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/6.
 */
public class ApiMain {
    public static void main(String[] args) {

        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6845&from_mid=1&&format=json&ie=utf-8&oe=utf-8&query=%E7%BD%91%E7%BB%9C%E5%B0%8F%E8%AF%B4&sort_key=&sort_type=1&stat0=%E6%A0%A1%E5%9B%AD&stat1=&stat2=&stat3=&pn=24&rn=8";
        String json = HttpUtils.getHtmlGet(url);
        Map<String, Object> map = JsonUtils.toMap(json);
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");

        Map<String, Object> map1=list.get(0);

        List<Map<String, Object>> map2=(List<Map<String, Object>>)map1.get("disp_data");
       for(Map<String, Object> map3:map2){
           System.out.println(map3.get("ename")+"_"+map3.get("sortHot"));
       }

    }
}
