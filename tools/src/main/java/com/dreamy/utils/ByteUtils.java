package com.dreamy.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wangyongxing on 16/4/8.
 */
public final class ByteUtils {

    /**
     * bytes转换成16进制
     *
     * @param bytes
     *            比特流
     * @return 16进制字符串
     */
    public static String toHex(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            StringBuilder buff = new StringBuilder(bytes.length << 1);
            String tmp = null;
            for (int i = 0; i < bytes.length; i++) {
                tmp = (Integer.toHexString(bytes[i] & 0xFF));
                if (tmp.length() == 1) {
                    buff.append('0');
                }
                buff.append(tmp);
            }
            return buff.toString();
        }

        return null;
    }

    /**
     * 16进制字符串转换为比特流
     *
     * @param hex
     *            16进制字符串
     * @return 比特流
     */
    public static byte[] fromHex(String hex) {
        if (hex != null && hex.length() > 1) {
            try {
                byte bytes[] = new byte[hex.length() / 2];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) Integer.parseInt(hex.substring(i << 1, (i << 1) + 2), 16);
                }
                return bytes;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 字符串转换为比特流
     *
     * @param str
     *            字符串
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes("UTF-8");
            } catch (Throwable e) {
            }
        }

        return null;
    }

    /**
     * String容器中数据统一转换为比特流集合
     *
     * @param ss
     *            String集合
     * @return 比特流集合
     */
    public static Collection<byte[]> getBytes(Collection<String> ss) {
        Collection<byte[]> rv = new ArrayList<byte[]>(ss.size());
        for (String s : ss) {
            rv.add(getBytes(s));
        }
        return rv;
    }

    /**
     * int转换为比特流
     *
     * @param res
     * @return
     */
    public static byte[] fromInt(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * 比特流转换为int
     *
     * @param res
     * @return
     */
    public static int toInt(byte[] res) {
        return (res[0] & 0xff) | ((res[1] << 8) & 0xff00) | ((res[2] << 24) >>> 8) | (res[3] << 24);
    }
}
