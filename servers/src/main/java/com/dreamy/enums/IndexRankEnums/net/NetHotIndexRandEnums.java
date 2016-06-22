package com.dreamy.enums.IndexRankEnums.net;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 上午10:16
 */
public enum NetHotIndexRandEnums {
    level_1(0, 500, 1000),
    level_2(501, 1000, 2000),
    level_3(1001, 1500, 3000),
    level_4(1501, 2000, 4000),
    level_5(2001, 3000, 5000),
    level_6(3001, 7000, 6000),
    level_7(7001, 30000, 7000),
    level_8(30001, 100000, 8000),
    level_9(100001, 600000, 9000),
    level_10(600001, 10000000, 9500),
    level_11(10000001, 100000000, 10000);
    private Integer start;
    private Integer end;
    private Integer score;

    NetHotIndexRandEnums(Integer start, Integer end, Integer score) {
        this.start = start;
        this.end = end;
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
