package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 16/6/16
 * Time: 上午11:21
 */
public enum NetBookPropagationIndexExchangeEnums {
    baidu(1, 0.01),
    weixin(2, 5.0),
    weibo(3, 1.0),
    s360(4, 0.1);

    private Integer type;

    private Double num;

    NetBookPropagationIndexExchangeEnums(Integer type, Double num) {
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
