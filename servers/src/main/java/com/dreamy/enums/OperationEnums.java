package com.dreamy.enums;

/**
 * Created by wangyongxing on 16/5/26.
 */
public enum  OperationEnums {

    crawler("0001", "抓取"),
    update("0002", "更新");

    private String code;

    private String description;

    OperationEnums(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
