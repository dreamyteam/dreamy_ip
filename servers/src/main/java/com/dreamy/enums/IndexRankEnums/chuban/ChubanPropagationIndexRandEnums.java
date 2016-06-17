package com.dreamy.enums.IndexRankEnums.chuban;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 上午10:16
 */
public enum ChubanPropagationIndexRandEnums {
    level_1(0, 85, 1000),
    level_2(86, 118, 2000),
    level_3(119, 203, 3000),
    level_4(204, 571, 4000),
    level_5(572, 2216, 5000),
    level_6(2217, 10444, 6000),
    level_7(10445, 31390, 7000),
    level_8(31391, 115921, 8000),
    level_9(115922, 293957, 9000),
    level_10(293958, 5000000, 9500),
    level_11(5000000, 100000000, 10000);
    private Integer start;
    private Integer end;
    private Integer score;

    ChubanPropagationIndexRandEnums(Integer start, Integer end, Integer score) {
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
