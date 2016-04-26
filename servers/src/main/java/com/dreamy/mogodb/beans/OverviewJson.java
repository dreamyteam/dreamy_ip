package com.dreamy.mogodb.beans;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wangyongxing on 16/4/21.
 */
public class OverviewJson {

    private String weekYearRatio;

    private String monthYearRatio;

    private String  weekChainRatio;

    private String monthChainRatio;

    private String weekIndex;

    private String monthIndex;

    public String getWeekYearRatio() {
        return weekYearRatio;
    }
    @JsonProperty(value = "week_year_ratio")
    public void setWeekYearRatio(String weekYearRatio) {
        this.weekYearRatio = weekYearRatio;
    }

    public String getMonthYearRatio() {
        return monthYearRatio;
    }
    @JsonProperty(value = "month_year_ratio")
    public void setMonthYearRatio(String monthYearRatio) {
        this.monthYearRatio = monthYearRatio;
    }

    public String getWeekChainRatio() {
        return weekChainRatio;
    }
    @JsonProperty(value = "week_chain_ratio")
    public void setWeekChainRatio(String weekChainRatio) {
        this.weekChainRatio = weekChainRatio;
    }

    public String getMonthChainRatio() {
        return monthChainRatio;
    }
    @JsonProperty(value = "month_chain_ratio")
    public void setMonthChainRatio(String monthChainRatio) {
        this.monthChainRatio = monthChainRatio;
    }

    public String getWeekIndex() {
        return weekIndex;
    }
    @JsonProperty(value = "week_index")
    public void setWeekIndex(String weekIndex) {
        this.weekIndex = weekIndex;
    }

    public String getMonthIndex() {
        return monthIndex;
    }
    @JsonProperty(value = "month_index")
    public void setMonthIndex(String monthIndex) {
        this.monthIndex = monthIndex;
    }
}
