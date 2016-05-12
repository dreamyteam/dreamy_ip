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
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        if(StringUtils.isNotEmpty(str)) {

            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        }
        else {
            return "0";
        }
    }


}
