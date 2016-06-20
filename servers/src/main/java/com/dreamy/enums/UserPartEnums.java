package com.dreamy.enums;

/**
 * Created by mac on 16/6/14.
 */
public enum UserPartEnums {

    type_personal(1, "个人"),
    type_business(2, "企业"),

    status_open(1, "启用"),
    status_close(2, "停用");

    private Integer value;
    private String description;

    UserPartEnums(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
