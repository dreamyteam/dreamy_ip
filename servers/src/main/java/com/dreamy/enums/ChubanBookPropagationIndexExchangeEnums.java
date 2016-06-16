package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/16
 * Time: 上午11:21
 */
public enum ChubanBookPropagationIndexExchangeEnums {
    amazon(1, 5.0),
    jd(2, 1.0),
    dangdang(3, 0.1),
    douban(4, 1.0),
    weibo(5, 1.0),
    s360(7, 1.0);

    private Integer type;

    private Double num;

    ChubanBookPropagationIndexExchangeEnums(Integer type, Double num) {
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
