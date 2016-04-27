package com.dreamy.handler;

import com.dreamy.mogodb.beans.Comment;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangyongxing on 16/4/18.
 */
@Component
public class CommentHandler {
    private static final Logger log = LoggerFactory.getLogger(CommentHandler.class);


    public List<Comment> getByUrlBak(String url) {
        List<Comment> comments = new ArrayList<Comment>();
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Elements elements = document.getElementsByClass("review-short");
                if (elements != null && elements.size() > 0) {

                    for (Element element : elements) {
                        try {
                            Comment bean = new Comment();
                            List<Element> list = element.children();
                            int size = list.size();
                            if (size >= 2) {

                                bean.setContent(list.get(0).text());
                                bean.setUrl(list.get(1).attr("href"));

                                if (size > 4) {
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
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }


                    }
                }
            }

        }
        return comments;
    }


    public List<Comment> getByUrl(String url) {
        List<Comment> comments = new ArrayList<Comment>();
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                Elements elements = document.getElementsByClass("tlst");
                for (Element element : elements) {
                    Comment bean = new Comment();
                    getAuthorAndImage(bean, element);
                    getTitleAndUrl(bean, element);
                    getContent(bean, element);
                    getCreateTime(bean,element);
                    comments.add(bean);

                }

            }
        }
        return comments;
    }

    /**
     * 获取评论人和图片
     *
     * @param comment
     * @param element
     */
    private void getAuthorAndImage(Comment comment, Element element) {
        try {
            Elements elements = element.getElementsByClass("pil");
            if (elements != null && elements.size() > 0) {
                Element first = elements.first();
                comment.setAuthor(first.attr("alt"));
                comment.setImage(first.attr("src"));
            }
        } catch (Exception e) {
            log.error("获取评论人和图片 失败 ", e);
        }

    }


    /**
     * 获取评论标题和url
     *
     * @param comment
     * @param element
     */
    private void getTitleAndUrl(Comment comment, Element element) {
        try {
            Elements elements = element.select("div.nlst>h3>a");
            if (elements != null && elements.size() > 0) {
                Element first = elements.first();
                comment.setTitle(first.attr("title"));
                comment.setUrl(first.attr("href"));
            }
        } catch (Exception e) {
            log.error("获取评论标题和url 失败 ", e);

        }

    }

    private void getContent(Comment comment, Element element) {
        try {
            Elements elements = element.select("div.clst>div.review-short>span");
            if (elements != null && elements.size() > 0) {
                Element first = elements.first();
                comment.setContent(first.text());
            }

        } catch (Exception e) {
            log.error("获取评论内容 失败 ", e);
        }

    }

    /**
     * 获取评论发布时间
     * @param comment
     * @param element
     */
    private void getCreateTime(Comment comment, Element element) {
        try {
            Elements elements =element.select("div.clst>div.review-short>div>span>span");
            if (elements != null && elements.size() > 0) {
                Element first = elements.first();
                comment.setCreateTime(first.text());
            }

        } catch (Exception e) {
            log.error("获取评论时间 失败 ", e);
        }

    }




}
