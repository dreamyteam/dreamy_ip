package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/16
 * Time: 上午11:21
 */
public enum ChubanBookReputationIndexExchangeEnums {
    amazon(1, 0.4),
    jd(2, 0.08),
    dangdang(3, 0.02),
    douban(4, 0.5);

    private Integer type;

    private Double num;

    ChubanBookReputationIndexExchangeEnums(Integer type, Double num) {
        this.type = type;
        this.num = num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }
}
