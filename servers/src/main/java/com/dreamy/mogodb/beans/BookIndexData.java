package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class BookIndexData {
    @Id
    private String id;

    private Integer bookId;

    private Integer source;
    /**
     * 搜索指数
     */
    private Integer index;
    /**
     * 媒体关注
     */
    private Integer media;
    /**
     * 地域分布
     */
    private List<SoArea> areaList;

    private List<SoTag> tags;

    private String female;

    private String male;

    private String age[];

    private  OverviewJson overviewJson;

    private String lastDate;

    private Date updatedAt;

    public String[] getAge() {
        return age;
    }

    public void setAge(String[] age) {
        this.age = age;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getMedia() {
        return media;
    }

    public void setMedia(Integer media) {
        this.media = media;
    }

    public List<SoArea> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<SoArea> areaList) {
        this.areaList = areaList;
    }

    public List<SoTag> getTags() {
        return tags;
    }

    public void setTags(List<SoTag> tags) {
        this.tags = tags;
    }

    public OverviewJson getOverviewJson() {
        return overviewJson;
    }

    public void setOverviewJson(OverviewJson overviewJson) {
        this.overviewJson = overviewJson;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
