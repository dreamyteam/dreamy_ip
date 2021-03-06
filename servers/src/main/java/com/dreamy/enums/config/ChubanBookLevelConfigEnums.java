package com.dreamy.enums.config;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/21
 * Time: 下午8:12
 */
public enum ChubanBookLevelConfigEnums {
    level_1(22.9, 1000, 2000),
    level_2(20.0, 2000, 3000),
    level_3(18.0, 3000, 4000),
    level_4(15.0, 4000, 5000),
    level_5(12.0, 5000, 6000),
    level_6(8.0, 6000, 7000),
    level_7(3.0, 7000, 8000),
    level_8(1.0, 8000, 9000),
    level_9(0.1, 9000, 9500);

    private Double percent;
    private Integer start;
    private Integer end;


    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    ChubanBookLevelConfigEnums(Double percent, Integer start, Integer end) {

        this.percent = percent;
        this.start = start;
        this.end = end;
    }
}
