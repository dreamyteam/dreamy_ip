package com.dreamy.beans.params;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/9
 * Time: 上午11:19
 */
public class LoginParams {
    private String mobile;
    private String password;
    private String ip;
    private String sessionId;
    private String rememberPwd;

    public String getRememberPwd() {
        return rememberPwd;
    }

    public void setRememberPwd(String rememberPwd) {
        this.rememberPwd = rememberPwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
