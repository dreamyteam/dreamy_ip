package com.dreamy.crawler.sina;

/**
 * Created by wangyongxing on 16/4/28.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dreamy.utils.sina.CrawSina;
import com.dreamy.utils.sina.LoginSina;
import com.dreamy.utils.sina.SinaHttpUtils;
import com.dreamy.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {//search_rese clearfix
        public static void main(String[] args) throws IOException {
            long startTime = System.currentTimeMillis();
            LoginSina ls = new LoginSina(CrawSina.weiboUsername, CrawSina.weiboPassword);
            ls.dologinSina();

            HttpClient client = new DefaultHttpClient();
            String url = "http://s.weibo.com/weibo/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9";
            HttpGet request = new HttpGet(url);
            request.setHeader("Cookie", CrawSina.Cookie);
            HttpResponse response = client.execute(request);
            String responseText = SinaHttpUtils.getStringFromResponse(response);
            client.getConnectionManager().shutdown();
            responseText=HttpUtils.decodeUnicode(responseText);


        }

    public static void main(String str) {

            Pattern p = Pattern.compile("找到(.*?)条结果");
            Matcher m = p.matcher(str);
            ArrayList<String> strs = new ArrayList<String>();
            while (m.find()) {
                strs.add(m.group(1));
            }
            for (String s : strs){
                System.out.println(s);
            }

    }

}
