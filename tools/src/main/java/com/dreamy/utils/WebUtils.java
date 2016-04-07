package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/1.
 */

import org.jsoup.Jsoup;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jared
 * @Description: web工具类
 * @date Nov 5, 2014 3:37:20 PM
 */
public final class WebUtils {

    public static final Cleaner CLEANER = new Cleaner(Whitelist.basicWithImages());

    /**
     * web请求二进制流转换成字符串
     *
     * @param request http request 请求
     * @return 字符串
     */
    public static String readToString(HttpServletRequest request) {
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String str = null;
            StringBuilder buff = new StringBuilder(request.getContentLength());
            while ((str = reader.readLine()) != null) {
                buff.append(str);
            }
            return buff.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuiet(reader);
        }
        return null;
    }

    /**
     * http请求头——文本
     *
     * @return
     */
    public static String getContentTypeText() {
        return "text/plain;charset=" + ConstUtils.Server.CHARSET;
    }

    /**
     * http请求头——xml
     *
     * @return
     */
    public static String getContentTypeXml() {
        return "application/xml;charset=" + ConstUtils.Server.CHARSET;
    }

    /**
     * http请求头——javascript
     *
     * @return
     */
    public static String getContentTypeJavascript() {
        return "application/javascript;charset=" + ConstUtils.Server.CHARSET;
    }

    /**
     * http请求头——json
     *
     * @return
     */
    public static String getContentTypeJson() {
        return "application/json;charset=" + ConstUtils.Server.CHARSET;
    }

    /**
     * 设置时间显示格式为http请求规划
     *
     * @param date
     * @return
     */
    public static String toString(Date date) {
        return new SimpleDateFormat(ConstStrings.DATE_FORMAT_PATTERN_HTTP, Locale.US).format(date == null ? new Date()
                : date);
    }

    /**
     * 获取当前请求的完整链接
     *
     * @param request
     * @return
     */
    public static String getFullRequestUrl(HttpServletRequest request) {
        StringBuffer buff = request.getRequestURL();
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            buff.append(ConstStrings.QUESTION_MARK);
            buff.append(escapeHtml(unescapeHtml(query)));
        }

        return buff.toString();
    }

    /**
     * 还原html标签
     *
     * @param source
     * @return
     */
    public static final String unescapeHtml(String source) {
        if (source == null || source.length() == 0) {
            return source;
        }
        StringBuilder buff = new StringBuilder(source.length());
        int i, j, skip = 0;
        boolean _continue;
        do {
            _continue = false;
            i = source.indexOf(ConstStrings.AMP, skip);
            if (i > -1) {
                j = source.indexOf(ConstStrings.SEMICOLON, i);
                if (j > i) {
                    String entity = ConstStrings.HTML_ENTITY_MAP.get(source.substring(i, j + 1));
                    if (entity != null) {
                        buff.append(source.substring(skip, i));
                        buff.append(entity);
                    } else {
                        buff.append(source.substring(skip, j + 1));
                    }
                    skip = j + 1;
                    _continue = true;
                    continue;
                }
            }

            buff.append(source.substring(skip));
        } while (_continue);
        return buff.toString();
    }

    /**
     * 转义html标签
     *
     * @param source
     * @return
     */
    public static String escapeHtml(String source) {
        if (source == null || source.length() == 0) {
            return ConstStrings.EMPTY;
        }
        StringBuilder buff = new StringBuilder((int) (source.length() * 1.3));
        for (int i = 0; i < source.length(); i++) {
            char _char = source.charAt(i);
            switch (_char) {
                case ConstCharacters.LT:
                    buff.append(ConstStrings.HTML_ENTITY_LT);
                    continue;
                case ConstCharacters.GT:
                    buff.append(ConstStrings.HTML_ENTITY_GT);
                    continue;
                case ConstCharacters.AMP:
                    buff.append(ConstStrings.HTML_ENTITY_AMP);
                    continue;
                case ConstCharacters.QUOTE:
                    buff.append(ConstStrings.HTML_ENTITY_QUOTE);
                    continue;
                case ConstCharacters.APOS:
                    buff.append(ConstStrings.HTML_ENTITY_APOS);
                    continue;
                default:
                    buff.append(_char);
            }
        }

        return buff.toString();
    }

    /**
     * url转码
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        if (url != null && url.length() > 0) {
            try {
                return URLEncoder.encode(url, ConstUtils.Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return url;
    }

    /**
     * url解码
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        if (url != null && url.length() > 0) {
            try {
                return URLDecoder.decode(url, ConstUtils.Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return url;
    }

    /**
     * 构建待参数的url
     *
     * @param uri        URL路径
     * @param parameters 参数map
     * @return
     */
    public static String buildUrl(String uri, Map<String, Object> parameters) {
        if (CollectionUtils.isNotEmpty(parameters)) {
            StringBuilder url = new StringBuilder();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if (entry != null) {
                    String value = String.valueOf(entry.getValue());
                    if (StringUtils.isNotEmpty(value)) {
                        if (url.length() == 0) {
                            url.append("?");
                        } else {
                            url.append("&");
                        }
                        url.append(entry.getKey());
                        url.append("=");
                        url.append(value);
                    }
                }
            }
            return url.insert(0, uri).toString();
        }
        return uri;
    }

    /**
     * 获取安全的html代码
     *
     * @param html
     * @return
     */
    public static String getSafetyHtml(String html) {
        if (StringUtils.isNotEmpty(html)) {
            return Jsoup.clean(html,
                    Whitelist.relaxed().addTags("fieldset", "section").addAttributes("fieldset", "class", "style")
                            .addAttributes("section", "class", "style").addAttributes("span", "class", "style")
                            .addAttributes("audio", "controls", "src", "data-name", "preload"));
        }
        return ConstStrings.EMPTY;
    }

    /**
     * 获取用户真实ip列表
     *
     * @param request
     * @return
     */
    private static String getRawRemoteAddress(HttpServletRequest request) {
        String ips = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (!StringUtils.contains(ips, ".", 2)) {
            ips = request.getHeader("X-Forwarded-For");
            if (!StringUtils.contains(ips, ".", 2)) {
                ips = request.getRemoteAddr();
            }
        }
        return ips;
    }

    /**
     * 获取用户ip路径
     *
     * @param request
     * @param removeBlanks
     * @return
     */
    public static String getRemoteAddressAll(HttpServletRequest request, boolean removeBlanks) {
        String ips = getRawRemoteAddress(request);
        if (StringUtils.isNotEmpty(ips)) {
            if (removeBlanks) {
                StringBuilder buff = new StringBuilder(ips.length());
                for (char c : ips.toCharArray()) {
                    if ((c >= '0' && c <= '9') || c == '.' || c == ',') {
                        buff.append(c);
                    }
                }
                return buff.toString();
            }
            return ips;
        }
        return ConstStrings.EMPTY;
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String[] ips = getRemoteAddressArray(request);
        if (ips != null && ips.length > 0) {
            for (String ip : ips) {
                if (ip != null && ip.length() >= 7) {
                    return ip;
                }
            }
        }

        return ConstStrings.EMPTY;
    }

    public static String[] getRemoteAddressArray(HttpServletRequest request) {
        String ips = getRemoteAddressAll(request, true);
        if (StringUtils.isNotEmpty(ips)) {
            return ips.split(ConstStrings.COMMA);
        }
        return null;
    }


    private static String zhPattern = "[\\u4e00-\\u9fa5]+";

    /**
     * 编码中文和空格，支持mp3下载
     *
     * @param str
     * @param charset
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encode(String str, String charset)
            throws UnsupportedEncodingException {
        str = str.replaceAll(" ", "%20");//对空字符串进行处理
        Pattern p = Pattern.compile(zhPattern);
        Matcher m = p.matcher(str);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
        }
        m.appendTail(b);
        return b.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }
}

