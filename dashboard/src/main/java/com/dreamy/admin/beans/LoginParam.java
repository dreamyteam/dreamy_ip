package com.dreamy.admin.beans;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class LoginParam {

    private String phone;
    private String password;
    private String ip;
    private String sessionId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /***
     * 工厂方法
     * @param phone
     * @param password
     * @param ip
     * @param sessionId
     * @return
     */
    public static LoginParam getNewInstance(String phone, String password, String ip, String sessionId) {
        LoginParam loginParam = new LoginParam();
        loginParam.setPhone(phone);
        loginParam.setPassword(password);
        loginParam.setIp(ip);
        loginParam.setSessionId(sessionId);
        return loginParam;
    }
}
