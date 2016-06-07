package com.dreamy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyongxing on 16/4/13.
 */
public final class PatternUtils {

    /***
     * 字符串 提取数字
     * @param str
     * @return
     */
    public static String getNum(String str){
        String result="";
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        if(StringUtils.isNotEmpty(str)) {
            Matcher m = p.matcher(str);
            result=m.replaceAll("").trim();
            if(StringUtils.isNotEmpty(result)){
                return result;
            }
            else{
                return "0";
            }
        }
        else {
            return "0";
        }
    }

    public static String date(String content) {
        String date = "";
        Pattern p = Pattern.compile("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/]");
        Matcher m = p.matcher(content);
        while (m.find()) {
            if (!"".equals(m.group())) {
                date = m.group();
            }
        }
        return date;
    }

    public static String extracte(String reg, String str) {
        String result = "0";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        while (m.find()) {
            result = m.group(1);
        }
        if (StringUtils.isEmpty(result)) {
            return "0";
        }
        return result;

    }


}
