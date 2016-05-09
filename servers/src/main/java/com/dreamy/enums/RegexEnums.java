package com.dreamy.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午12:37
 */
public enum RegexEnums {
    mobile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"),
    email("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),
    ip("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)");
    private String regex;


    RegexEnums(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
