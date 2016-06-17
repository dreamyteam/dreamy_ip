package com.dreamy.enums.IndexRankEnums.chuban;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 上午10:16
 */
public enum ChubanReputationIndexRandEnums {
    level_1(0, 17, 1000),
    level_2(18, 36, 2000),
    level_3(37, 110, 3000),
    level_4(111, 399, 4000),
    level_5(400, 1588, 5000),
    level_6(1589, 6409, 6000),
    level_7(6410, 21067, 7000),
    level_8(21068, 64127, 8000),
    level_9(64128, 126914, 9000),
    level_10(126915, 20000000, 9500),
    level_11(20000001, 100000000, 10000);
    private Integer start;
    private Integer end;
    private Integer score;

    ChubanReputationIndexRandEnums(Integer start, Integer end, Integer score) {
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
