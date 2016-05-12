package com.dreamy.crawler.sina;

import com.dreamy.utils.sina.CrawSina;
import com.dreamy.utils.sina.LoginSina;
import com.dreamy.utils.sina.SinaHttpUtils;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/4/20.
 */
public class IndexMain {
    public static void main(String[] args) {


//        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), Baidu.class);
//        //single download
        // String url="https://www.baidu.com/s?wd=%E5%A4%A7%E4%B8%BB%E5%AE%B0&rsv_spt=1&rsv_iqid=0xef68a40a000060b5&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=13&rsv_sug1=19&rsv_sug7=101&rsv_sug2=0&inputT=6320&rsv_sug4=6492&rsv_sug=1";
//        Baidu baike = ooSpider.<Baidu>get(url);
//        System.out.println(baike.getNum());
//        ooSpider.close();
        String name = "大主宰";
        name = HttpUtils.encodeUrl(name);
        String url = "https://www.baidu.com/s?wd=" + name;


        String html = HttpUtils.getHtmlGet(url);
        //System.out.println(html);
        Document document = Jsoup.parse(html);
        if (document != null) {
            Elements elements = document.getElementsByClass("nums");

            if(elements!=null&&elements.size()>0)
            {
                Element element=elements.first();
                System.out.println(element.text());
                String aa = element.text();
                System.out.println(PatternUtils.getNum(aa));
            }
//            for (Element element : elements) {
//                System.out.println(element.text());
//                String aa = element.text();
//                System.out.println(PatternUtils.getNum(aa));
//            }
        }


    }

    public static void main2(String[] args) {
        String url = "https://www.so.com/s?ie=utf-8&shb=1&src=home_so.com&q=爱奇艺";
        String html = HttpUtils.getSsl(url);
        Document document = Jsoup.parse(html);
        if (document != null) {
            Elements elements = document.getElementsByClass("nums");
            for (Element element : elements) {
                String bb = element.text();
                System.out.println(PatternUtils.getNum(bb));
            }
        }

    }

    public static void main1(String[] args) {
        String name = "大主宰";
        name = HttpUtils.encodeUrl(name);
        String url = "http://weixin.sogou.com/weixin?type=2&query=" + name;
        String html = HttpUtils.getHtmlGet(url);
        Document document = Jsoup.parse(html);
        if (document != null) {
            Element element = document.getElementById("scd_num");
            if (element != null) {
                String aa = element.text();
                PatternUtils.getNum(aa);
            }
        }
    }

    public static void main11(String[] args) throws IOException {

        LoginSina ls = new LoginSina(CrawSina.weiboUsername, CrawSina.weiboPassword);
        ls.dologinSina();
        HttpClient client = new DefaultHttpClient();
        String url = "http://s.weibo.com/weibo/%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9";
        HttpGet request = new HttpGet(url);
        request.setHeader("Cookie", CrawSina.Cookie);
        HttpResponse response = client.execute(request);
        String responseText = SinaHttpUtils.getStringFromResponse(response);
        client.getConnectionManager().shutdown();
        responseText = HttpUtils.decodeUnicode(responseText);
        System.out.println(sina(responseText));
    }


    public static String sina(String str) {

        Pattern p = Pattern.compile("找到(.*?)条结果");
        Matcher m = p.matcher(str);
        String result = "";
        while (m.find()) {
            result = m.group(1);
        }
        return result;

    }

}
