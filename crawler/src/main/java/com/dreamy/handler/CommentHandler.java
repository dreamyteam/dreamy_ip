package com.dreamy.handler;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.mogodb.beans.Comment;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


/**
 * Created by wangyongxing on 16/4/18.
 */
@Component
public class CommentHandler {


    public Comment getByUrl(Integer ipId,String url) {
        String html = HttpUtils.getHtmlGet(url, "null");
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Comment bean = new Comment();
                Elements infos = document.select("div.content >ul>li");
                return null;
            }
        }
        return null;
    }

}
