package com.dreamy.enums.IndexRankEnums.net;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 上午10:16
 */
public enum NetActivityRandEnums {
    level_1(0, 5000, 1000),
    level_2(5001, 10000, 2000),
    level_3(10001, 50000, 3000),
    level_4(50001, 100000, 4000),
    level_5(100001, 200000, 5000),
    level_6(200001, 500000, 6000),
    level_7(500001, 1000000, 7000),
    level_8(1000001, 10000000, 8000),
    level_9(10000001, 50000000, 9000),
    level_10(50000001, 100000000, 9500),
    level_11(100000001, 1000000000, 10000);
    private Integer start;
    private Integer end;
    private Integer score;

    NetActivityRandEnums(Integer start, Integer end, Integer score) {
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
