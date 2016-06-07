package com.dreamy.crawler.handler.info.netbook.zh;

import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.NetBookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.JsonUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wangyongxing on 16/5/31.
 */
@Component
public class ZongHengHandler {
    private static final Logger log = LoggerFactory.getLogger(ZongHengHandler.class);

    public NetBookInfo crawler(Integer bookId, String url, String operation) {
        String html = HttpUtils.getHtmlGet(url);
        NetBookInfo info = null;
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                info = new NetBookInfo();
                String code = PatternUtils.getNum(url);
                info.setCode(Integer.valueOf(code));
                info.setBookId(bookId);
                if (operation.equals(OperationEnums.crawler.getCode())) {
                    getImage(info, document);
                    getInfo(info, document);
                }
                getClickNum(info, document);
                getTicketNumAndSort(info);

            }
        } else {
            log.info(" zongheng  crawler is empty url=" + url + " book=" + bookId);

        }
        return info;

    }

    public static void getImage(NetBookInfo info, Document document) {

        Elements elements = document.select("div.book_cover>p>a>img");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setImage(element.attr("src"));
        }


    }

    public static void getInfo(NetBookInfo info, Document document) {

        Elements elements = document.select("div.info_con>p");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            info.setInfo(element.text());
        }

    }

    /**
     * 解析 点击数 推荐数 评论数
     *
     * @param info
     * @param document
     */
    public static void getClickNum(NetBookInfo info, Document document) {
        try {
            Elements elements = document.select("div.vote_info>p");
            if (elements != null && elements.size() > 0) {
                Element celement = elements.get(2);
                String clickNum = celement.text();
                info.setClickNum(Integer.valueOf(PatternUtils.getNum(clickNum)));
                Element relement = elements.get(4);
                String recommendNum = relement.text();
                info.setRecommendNum(Integer.valueOf(PatternUtils.getNum(recommendNum)));
                Element commentElement = elements.get(5);
                String commentNum = commentElement.text();
                info.setCommentNum(Integer.valueOf(PatternUtils.getNum(commentNum)));

            }
        } catch (Exception e) {

            log.error(" zongheng 点击数 is error book=" + info.getBookId(), e);


        }

    }


    private void getTicketNumAndSort(NetBookInfo info) {

        String url = "http://book.zongheng.com/book/async/info.htm?bookId=" + info.getCode();
        String json = HttpUtils.getHtmlGet(url);
        try {
            Map<String, Object> map = JsonUtils.toMap(json);
            Map<String, Object> map1 = (Map<String, Object>) map.get("monthTicket");
            Map<String, Object> map2 = (Map<String, Object>) map1.get("rank");
            info.setTicketNum((Integer) map2.get("number"));
            info.setMonthSort(map2.get("orderNo")+"");
        } catch (Exception e) {
            log.error(" zongheng 月票数 is error book=" + info.getBookId(), e);
        }


    }

}
