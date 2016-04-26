package com.dreamy.mogodb.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by wangyongxing on 16/4/21.
 */
public class SoObject {
    private String query;

    private OverviewJson data;

    private String age;

    private String female;

    private String male;

    private List<SoTag> tags;

    private List<SoArea> list;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    @JsonProperty(value = "data")
    public OverviewJson getData() {
        return data;
    }
    @JsonProperty(value = "data")
    public void setData(OverviewJson data) {
        this.data = data;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public List<SoTag> getTags() {
        return tags;
    }

    public void setTags(List<SoTag> tags) {
        this.tags = tags;
    }

    public List<SoArea> getList() {
        return list;
    }

    public void setList(List<SoArea> list) {
        this.list = list;
    }
}
