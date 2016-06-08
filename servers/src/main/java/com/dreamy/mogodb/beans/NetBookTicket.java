package com.dreamy.mogodb.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by wangyongxing on 16/6/7.
 */
@Document(collection = "netBookTicket_huayu")
@TypeAlias("netBookTicket_huayu")
public class NetBookTicket {

    @Id
    private Integer code;
    private Integer ticketNum = 0;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

}
