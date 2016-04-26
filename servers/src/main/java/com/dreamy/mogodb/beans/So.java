package com.dreamy.mogodb.beans;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by wangyongxing on 16/4/21.
 */
public class So {
    private  Integer status;
    private SoObject data;
    public Integer getStatus() {
        return status;
    }
    @JsonProperty(value = "status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public SoObject getData() {
        return data;
    }
    @JsonProperty(value = "data")
    public void setData(SoObject data) {
        this.data = data;
    }


}
