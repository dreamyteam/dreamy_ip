package com.dreamy.enums;

/**
 * Created by mac on 16/6/15.
 */
public enum UserAuthEnums {

    type_personal(1, "个人"),
    type_business(2, "企业"),

    status_applying(1, "申请中"),
    status_apply_pass(2, "申请通过"),
    status_apply_not_pass(3, "申请不通过");

    private Integer value;
    private String description;

    UserAuthEnums(Integer value, String description) {
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
