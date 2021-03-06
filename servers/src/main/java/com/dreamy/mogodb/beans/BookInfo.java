package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by wangyongxing on 16/4/5.
 */
@Document(collection = "bookInfo")
@TypeAlias("bookInfo")
public class BookInfo {
    @Id
    private String id;

    private Integer crawlerId;
    /**
     * 作品名称
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版时间
     */
    private String pushTime;
    /**
     * 出版社
     */
    private String press;
    /**
     * 销售排名
     */
    private Integer saleSort;
    /**
     * 封面
     */
    private String image;
    /**
     * 作品简介
     */
    private String info;
    /**
     * 标签
     */
    private String lable;

    /**
     * 评分
     */
    private Double score;
    /**
     * 作者简介
     */
    private String authorInfo;

    private String tags;

    private Integer ipId;

    private Integer commentNum;

    private String categories;

    private String editorComment;
    /**
     * 抓取来源
     */
    private Integer source;

    private String ISBN;

    private String url;

    public Integer getCrawlerId() {
        return crawlerId;
    }

    public void setCrawlerId(Integer crawlerId) {
        this.crawlerId = crawlerId;
    }

    public String getEditorComment() {
        return editorComment;
    }

    public void setEditorComment(String editorComment) {
        this.editorComment = editorComment;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getSaleSort() {
        return saleSort;
    }

    public void setSaleSort(Integer saleSort) {
        this.saleSort = saleSort;
    }
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }


    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
