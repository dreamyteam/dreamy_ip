package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class BookIndexData {
    @Id
    private Integer id;

    private Integer source;
    /**
     * 搜索指数
     */
    private String index[];
    /**
     * 媒体关注
     */
    private String media[];
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String[] getIndex() {
        return index;
    }

    public void setIndex(String[] index) {
        this.index = index;
    }

    public String[] getMedia() {
        return media;
    }

    public void setMedia(String[] media) {
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
}
