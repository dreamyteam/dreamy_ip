package com.dreamy.crawler.handler.info.amazon;


import com.dreamy.enums.OperationEnums;
import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.PatternUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AmazonCrawlerBookHandler {
    private static final Logger log = LoggerFactory.getLogger(AmazonCrawlerBookHandler.class);

    public BookInfo getByISBN(String isbn, String operation) {

        //String url = "https://www.amazon.cn/s/ref=nb_sb_noss?__mk_zh_CN=亚马逊网站&url=search-alias%3Dstripbooks&field-keywords=" + isbn + "&rh=n%3A658390051%2Ck%3A9787535438171";
        String url = "https://www.amazon.cn/s/ref=sr_st_review-rank?keywords=" + isbn + "&rh=n%3A658390051%2Ck%3A9787535438171&qid=1465986426&__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&sort=review-rank&ajr=2";
        String html = HttpUtils.getHtmlGet(url);
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.a-fixed-left-grid-inner>div>div>a");
        if (elements != null && elements.size() >= 1) {
            Element element = elements.first();
            String crawlerUrl = element.attr("href");
            BookInfo bookInfo = crawler(crawlerUrl, operation);
            return bookInfo;

        }

        return null;

//        try {
//            OOSpider ooSpider = OOSpider.create(Site.me().setTimeOut(10000), AmazonBean.class);
//            AmazonBean amazonBean = ooSpider.<AmazonBean>get(url);
//            ooSpider.close();
//            String crawlerUrl = "";
//            if (amazonBean != null) {
//                List<String> list = amazonBean.getUrls();
//                if (CollectionUtils.isNotEmpty(list)) {
//                    crawlerUrl = list.get(0);
//                }
//                BookInfo bookInfo = crawler(crawlerUrl, operation);
//                return bookInfo;
//            }
//        } catch (Exception e) {
//            log.error("AmazonCrawlerBookHandler getByISBN url:" + url, e);
//        }
//        return null;

    }

    public BookInfo crawler(String url, String operation) {
        String html = HttpUtils.getHtmlGet(url);
        if (StringUtils.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            if (document != null) {
                BookInfo bean = new BookInfo();
                if (operation.equals(OperationEnums.crawler.getCode())) {//判断抓取还是更新操作
                    bean.setUrl(url);
                    getName(bean, document);
                    getAuthor(bean, document);
                    getPressAndPublishTime(bean, document);
                    getCoverImg(bean, document);
                    getIpDescription(bean, document);
                    getCategories(bean, document);
                    getEditorComments(bean, document);
                    getAuthorDescrition(bean, document);
                    getTags(bean, document);
                }
                getSaleSort(bean, document);
                getTotalCommentNumAndScore(bean, document);
                return bean;
            }

        }
        return null;
    }

    /**
     * 获取书名
     *
     * @param bean
     * @param document
     */

    private void getName(BookInfo bean, Document document) {
        try {
            Element element = document.getElementById("productTitle");
            if (element != null) {
                bean.setTitle(element.text());
            }
        } catch (Exception e) {
            log.error("解析 amazon 标题失败", e);
        }

    }

    /**
     * 获取作者
     *
     * @param bean
     * @param document
     */
    private void getAuthor(BookInfo bean, Document document) {
        String author = "";
        Elements elements = document.getElementsByClass("author");
        try {
            for (Element element : elements) {
                author += element.child(0).text();
                if ("contribution".equals(element.child(1).className())) {
                    author += element.child(1).child(0).text();
                }
            }
        } catch (Exception e) {
            log.error("解析 amazon 作者失败", e);
        } finally {
            bean.setAuthor(author);
        }

    }


    /**
     * 出版社和出版时间
     *
     * @param bean
     * @param document
     */
    private void getPressAndPublishTime(BookInfo bean, Document document) {
        String press = "";
        String publishTime = "";
        try {
            Element element = document.getElementById("detail_bullets_id");
            if (element != null) {
                Elements elements = element.select("div>ul>li");
                String[] data = elements.get(0).text().substring(4).split(";");
                press = data[0];
                String pressInfo = (data[1].split("\\("))[1];
                publishTime = pressInfo.substring(0, pressInfo.length() - 2);
                publishTime = publishTime.replaceFirst("年", "-");
                publishTime = publishTime.replaceFirst("月", "-");

            }
        } catch (Exception e) {
            log.error("解析 amazon 出版社和出版时间 异常", e);
        } finally {
            bean.setPushTime(publishTime);
            bean.setPress(press);
        }
    }


    /**
     * 总的评论数和平台评分
     *
     * @param bean
     * @param document
     */
    private void getTotalCommentNumAndScore(BookInfo bean, Document document) {
        Integer commentNumString = 0;
        double score = 0.0;
        try {
            Element star = document.getElementById("summaryStars");
            if (star != null) {
                Element element = star.getElementsByTag("a").first();
                String[] scoreAndComment = element.text().split("星");
                commentNumString = Integer.parseInt(scoreAndComment[1].replaceFirst(",", "").substring(1));
                String result = scoreAndComment[0].substring(2, scoreAndComment[0].length() - 1);
                score = Double.valueOf(result);

            }
        } catch (NumberFormatException e) {
            log.error("解析amazon评论总数和评分异常", e);
        } finally {
            bean.setCommentNum(commentNumString);
            bean.setScore(score);
        }

    }

    /**
     * 图书销售排名
     *
     * @param bean
     * @param document
     */
    private void getSaleSort(BookInfo bean, Document document) {
        String saleSort = "";
        try {
            Element sort = document.getElementById("SalesRank");
            if (sort != null) {
                String[] saleRank = sort.childNode(2).toString().split("第");
                saleSort = saleRank[1].substring(0, saleRank[1].length() - 3);
            }
        } catch (Exception e) {
            log.error("解析 amazon 图书销售排名 异常", e);
        } finally {
            bean.setSaleSort(Integer.valueOf(PatternUtils.getNum(saleSort)));
        }

    }

    /**
     * 封面
     *
     * @param bean
     * @param document
     */
    private void getCoverImg(BookInfo bean, Document document) {
        String imageUrl = "";
        try {
            Element element = document.getElementById("imgBlkFront");
            String dynamicImages = element.attr("data-a-dynamic-image");
            imageUrl = (dynamicImages.split("\""))[1];
        } catch (Exception e) {
            log.error("解析 amazon 封面 异常", e);
        } finally {
            bean.setImage(imageUrl);
        }
    }

    /**
     * 获取ip的描述
     *
     * @param bean
     * @param document
     */
    private void getIpDescription(BookInfo bean, Document document) {
        String description = "";
        Elements noscripts = document.getElementsByTag("noscript");
        try {
            if (noscripts != null && noscripts.size() > 2) {
                description = noscripts.get(1).text();
            }
        } catch (Exception e) {
            log.error("解析 amazon 图书描述", e);
        } finally {
            bean.setInfo(description);
        }

    }

    /**
     * 获取分类
     *
     * @param bean
     * @param document
     */
    private void getCategories(BookInfo bean, Document document) {
        String categories = "";
        try {
            Element element = document.getElementById("SalesRank");
            if (element != null) {
                Elements elements = element.getElementsByClass("zg_hrsr_item");
                int size = elements.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        Element currentElement = elements.get(i);
                        String[] rankString = currentElement.child(0).text().split("第");
                        String rank = rankString[1].substring(0, rankString[1].length() - 1);

                        String parentCategory = currentElement.child(1).child(1).text();
                        if (currentElement.child(1).childNodes().size() > 5) {
                            String subParentCategory = currentElement.child(1).child(2).text();
                            categories += parentCategory + "|" + subParentCategory + ":" + rank + ",";
                        } else {
                            categories += parentCategory + ":" + rank + ",";
                        }
                    }
                    categories = categories.substring(0, categories.length() - 1);
                }
            }

        } catch (Exception e) {
            log.error("解析 amazon 获取分类 异常", e);
        } finally {
            bean.setCategories(categories);
        }
    }

    /**
     * 编辑评论
     *
     * @param bean
     * @param document
     */
    private void getEditorComments(BookInfo bean, Document document) {
        String comment = "";

        try {
            Element element = document.getElementById("s_content_0");
            if (element != null) {
                comment = element.child(1).text();
            }
        } catch (Exception e) {
            log.error("解析 amazon 编辑推荐异常", e);
        } finally {
            bean.setEditorComment(comment);
        }

    }


    /**
     * 标签
     *
     * @param bean
     * @param document
     */
    private void getTags(BookInfo bean, Document document) {


    }

    /**
     * 获取作者描述
     *
     * @param bean
     * @param document
     */
    private void getAuthorDescrition(BookInfo bean, Document document) {
        String authorDescription = "";
        try {
            Element element = document.getElementById("detail_bullets_id");
//            if (element != null) {
//                String asin = "";
//                Elements elements = element.select("div>ul>li");
//                for (Element e : elements) {
//                    if (e.text().contains("ASIN: ")) {
//                        asin = e.text().substring(6);
//                        String html = HttpUtils.getHtmlGet("https://www.amazon.cn/gp/product-description/ajaxGetProuductDescription.html?ref_=dp_apl_pc_loaddesc&merchantId=A1AJ19PSB66TGU&deviceType=web&asin=" + asin);
//                        if (StringUtils.isNotEmpty(html)) {
//                            Document userInfoDocument = Jsoup.parse(html);
//                            Elements es = userInfoDocument.getElementsByClass("s-content");
//                            for (Element one : es) {
//                                String text = one.text();
//                                if (text.contains("作者简介")) {
//                                    authorDescription = one.child(1).text();
//                                    break;
//                                }
//                            }
//                        }
//                        break;
//                    }
//                }
//
//            }

        } catch (Exception e) {
            log.error("解析 amazon 作者描述 异常", e);
        } finally {
            bean.setAuthorInfo(authorDescription);
        }
    }

    public static void main(String[] args) {
        String isbn = "9787535438171";
        String url = "https://www.amazon.cn/s/ref=sr_st_review-rank?keywords=" + isbn + "&rh=n%3A658390051%2Ck%3A9787535438171&qid=1465986426&__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&sort=review-rank&ajr=2";
        String html = HttpUtils.getHtmlGet(url);
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.a-fixed-left-grid-inner>div>div>a");
        if (elements != null && elements.size() > 0) {
            Element element = elements.first();
            System.out.println(element.attr("href"));
        }


        System.out.println(111);
    }
}
