package com.dreamy.handler;

import com.dreamy.mogodb.beans.Comment;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangyongxing on 16/4/18.
 */
@Component
public class CommentHandler {


    public List<Comment> getByUrl(String url) {
        List<Comment> comments=new ArrayList<Comment>();
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Elements elements = document.getElementsByClass("review-short");
                if(elements!=null&&elements.size()>0) {

                    for (Element element : elements) {
                        try {
                            Comment bean = new Comment();
                            List<Element> list = element.children();
                            int size = list.size();
                            if (size >= 2) {

                                bean.setContent(list.get(0).text());
                                bean.setUrl(list.get(1).attr("href"));

                                if (size >4) {
                                    Elements spans = list.get(4).getElementsByTag("span");
                                    if (spans != null && spans.size() > 2) {
                                        bean.setCreateTime(spans.get(1).text());
                                    }

                                } else {
                                    Elements spans = list.get(3).getElementsByTag("span");
                                    if (spans != null && spans.size() > 2) {
                                        bean.setCreateTime(spans.get(1).text());
                                    }

                                }
                                comments.add(bean);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            continue;
                        }


                    }
                }
            }

        }
        return comments;
    }


}
