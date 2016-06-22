package com.dreamy.enums.IndexRankEnums.net;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/17
 * Time: 上午10:16
 */
public enum NetPropagationIndexRandEnums {
    level_1(0, 2000, 1000),
    level_2(2001, 4000, 2000),
    level_3(4001, 7000, 3000),
    level_4(7001, 10000, 4000),
    level_5(10001, 20000, 5000),
    level_6(20001, 50000, 6000),
    level_7(50001, 100000, 7000),
    level_8(100001, 1000000, 8000),
    level_9(1000001, 10000000, 9000),
    level_10(10000001, 100000000, 9500),
    level_11(100000001, 1000000000, 10000);
    private Integer start;
    private Integer end;
    private Integer score;

    NetPropagationIndexRandEnums(Integer start, Integer end, Integer score) {
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
