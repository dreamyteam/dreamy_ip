package com.dreamy.utils;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 下午3:15
 */
public class PasswordUtils {


    /**
     * MD5加密密码
     *
     * @param password
     * @return
     */
    public static String createPassword(String password) {
        if (StringUtils.isNotEmpty()) {
            password = "123456";
        }

        return HashUtils.md5(password);
    }


    /**
     * 验证输入密码是否与MD5加密密码相符
     * 之前的加密未采用Spring Security的内置方法，得到的结果是大写字符串，现统一为小写
     *
     * @param encPass MD5加密密码
     * @param rawPass 原始密码
     * @return
     */
    public static boolean isPasswordInvalid(String encPass, String rawPass) {
        Boolean res = true;
        if (StringUtils.isEmpty(rawPass)) {
            res = false;
        } else {
            if (!encPass.equals(HashUtils.md5(rawPass))) {
                res = false;
            }
        }
        return res;
    }


}
