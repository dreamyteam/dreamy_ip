package com.dreamy.enums.IndexRankEnums.chuban;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 上午10:16
 */
public enum ChubanHotIndexRandEnums {
    level_1(0, 65, 1000),
    level_2(66, 143, 2000),
    level_3(144, 448, 3000),
    level_4(449, 1515, 4000),
    level_5(1516, 5537, 5000),
    level_6(5538, 21360, 6000),
    level_7(21361, 65281, 7000),
    level_8(65282, 214262, 8000),
    level_9(214263, 610216, 9000),
    level_10(610217, 10000000, 9500),
    level_11(10000001, 100000000, 10000);
    private Integer start;
    private Integer end;
    private Integer score;

    ChubanHotIndexRandEnums(Integer start, Integer end, Integer score) {
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
