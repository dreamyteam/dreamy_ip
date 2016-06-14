package com.dreamy.crawler.handler.tieba;

import com.dreamy.mogodb.beans.tieba.TieBa;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PatternUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyongxing on 16/6/12.
 */
@Component
public class TieBaHandler {

    public TieBa crawler(String word, Integer bookId) {
        TieBa tieBa = null;
        word = HttpUtils.encodeUrl(word);
        String url = "http://tieba.baidu.com/f?ie=utf-8&fr=search&kw=" + word;
        String html = HttpUtils.getHtmlGet(url);
        String code = PatternUtils.extracte("\"forum_id\":([0-9]*)", html);
        if (!code.equals("0")) {
            tieBa = new TieBa();
            tieBa.setBookId(bookId);
            tieBa.setCode(code);
            sort(tieBa, code);
            html = html.replace("<!--", "").replace("-->", "");
            Document document = Jsoup.parse(html);
            title(tieBa, document);
            category(tieBa, document);
            followNum(tieBa, document);
            postNum(tieBa, document);
        }
        return tieBa;

    }


    public static void sort(TieBa tieBa, String code) {
        String ajaxUrl = "http://tieba.baidu.com/novel/getMonthTicketRankTopN?count=10&forum_id=" + code;
        String json = HttpUtils.getHtmlGet(ajaxUrl);
        Map<String, Object> map = JsonUtils.toMap(json);
        Map<String, Object> map1 = (Map<String, Object>) map.get("data");
        List<Map<String, Object>> list = (List<Map<String, Object>>) map1.get("rank_list");
        if (list.size() > 2) {
            Map<String, Object> map2 = list.get(2);
            int sort = (Integer) map2.get("ranking");
            tieBa.setPopularitySort(sort);
        }
    }

    public static void title(TieBa tieBa, Document document) {

        Elements elements = document.getElementsByClass("card_title_fname");
        Element element = elements.first();
        tieBa.setTitle(element.text());

    }

    public static void category(TieBa tieBa, Document document) {

        Elements elements = document.select("ul.forum_dir_info>li>a");
        Element element = elements.first();
        tieBa.setCategory(element.text());

    }

    public static void followNum(TieBa tieBa, Document document) {
        Elements elements = document.getElementsByClass("card_menNum");
        Element element = elements.first();
        String num = element.text().replace(",", "");
        tieBa.setFollowNum(Integer.valueOf(num));
    }

    public static void postNum(TieBa tieBa, Document document) {

        Elements elements = document.getElementsByClass("card_infoNum");
        Element element = elements.first();
        String num = element.text().replace(",", "");
        tieBa.setPostNum(Integer.valueOf(num));
    }

    public static void main(String[] args) {
        String word = "大主宰";
        word = HttpUtils.encodeUrl(word);
        String url = "http://tieba.baidu.com/f?ie=utf-8&fr=search&kw=" + word;
        String html = HttpUtils.getHtmlGet(url);
        String code = PatternUtils.extracte("\"forum_id\":([0-9]*)", html);
        if (!code.equals("0")) {
            TieBa tieBa = new TieBa();
            tieBa.setCode(code);
            sort(tieBa, code);
            html = html.replace("<!--", "").replace("-->", "");
            Document document = Jsoup.parse(html);
            title(tieBa, document);
            category(tieBa, document);
            followNum(tieBa, document);
            postNum(tieBa, document);

        }

    }
}
