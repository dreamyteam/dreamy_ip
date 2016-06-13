package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;

/**
 * Created by wangyongxing on 16/6/13.
 */
public class BookIndexDataHistory {

    @Id
    private String id;

    private Integer bookId;

    private Integer source;

    private String female;

    private String male;

    private String createDate;

    private Integer mediaNum;

    private Integer searchNum;

    private String weekYearRatio;

    private String monthYearRatio;

    private String  weekChainRatio;

    private String monthChainRatio;

    private Integer weekIndex;

    private Integer monthIndex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getMediaNum() {
        return mediaNum;
    }

    public void setMediaNum(Integer mediaNum) {
        this.mediaNum = mediaNum;
    }

    public String getWeekYearRatio() {
        return weekYearRatio;
    }

    public void setWeekYearRatio(String weekYearRatio) {
        this.weekYearRatio = weekYearRatio;
    }

    public String getMonthYearRatio() {
        return monthYearRatio;
    }

    public void setMonthYearRatio(String monthYearRatio) {
        this.monthYearRatio = monthYearRatio;
    }

    public String getWeekChainRatio() {
        return weekChainRatio;
    }

    public void setWeekChainRatio(String weekChainRatio) {
        this.weekChainRatio = weekChainRatio;
    }

    public String getMonthChainRatio() {
        return monthChainRatio;
    }

    public void setMonthChainRatio(String monthChainRatio) {
        this.monthChainRatio = monthChainRatio;
    }

    public Integer getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(Integer weekIndex) {
        this.weekIndex = weekIndex;
    }

    public Integer getMonthIndex() {
        return monthIndex;
    }

    public void setMonthIndex(Integer monthIndex) {
        this.monthIndex = monthIndex;
    }

    public Integer getSearchNum() {
        return searchNum;
    }

    public void setSearchNum(Integer searchNum) {
        this.searchNum = searchNum;
    }
}
