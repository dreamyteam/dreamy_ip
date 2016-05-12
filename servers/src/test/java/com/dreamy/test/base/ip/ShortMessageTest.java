package com.dreamy.test.base.ip;

import com.dreamy.utils.HttpUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 16/5/10
 * Time: 上午11:47
 */
public class ShortMessageTest {
    @Test
    public void send() {
        Map<String, String> map = new HashMap<>();
        map.put("apikey", "203a5195ff139204e26ae66480a5b98d");
        map.put("mobile", "18268125687");
        map.put("text", "【IP库】您的验证码是1989");

        String res = HttpUtils.post("https://sms.yunpian.com/v2/sms/single_send.json", map);
        System.err.println(res);
    }
}
