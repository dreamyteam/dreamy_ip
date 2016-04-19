package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/6.
 */

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jared
 *
 * @Description: http请求相关工具类
 *
 * @date Nov 5, 2014 3:34:29 PM
 *
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * get方法获取网页
     *
     * @param tempurl
     *            网页链接
     * @return
     */
    public static String getHtmlGet(String tempurl) {
        return getHtmlGet(tempurl, "utf-8", null, 0, null, null, null, null);
    }


    public static String getHtmlGetBycharSet(String tempurl,String charSet ) {
        return getHtmlGet(tempurl,charSet, null, 0, null, null, null, null);
    }


    /**
     * get方法获取网页
     *
     * @param tempurl
     * @param referer
     * @return
     */
    public static String getHtmlGet(String tempurl, String referer) {
        return getHtmlGet(tempurl, "utf-8", null, 0, null, null, referer, null);
    }

    /**
     * 配置请求头,get方法获取网页
     *
     * @param tempurl
     * @param referer
     * @param XRequestedWith
     * @return
     */
    public static String getHtmlGet(String tempurl, String referer, String XRequestedWith) {
        return getHtmlGet(tempurl, "utf-8", null, 0, null, null, referer, XRequestedWith);
    }

    /**
     * get方法获取网页
     *
     * @param tempurl
     *            网页链接
     * @param charSet
     *            网页字符类型
     * @param proxyHost
     *            代理ip
     * @param proxyPort
     *            代理端口
     * @param cookie
     *            cookie
     * @param userAgent
     *            userAgent
     * @param referer
     *            referer
     * @return
     */
    public static String getHtmlGet(String tempurl, String charSet, String proxyHost, int proxyPort, String cookie,
                                    String userAgent, String referer, String XRequestedWith) {
        HttpClient client = new HttpClient();
        if (StringUtils.isNotEmpty(proxyHost)) {
            client.getHostConfiguration().setProxy(proxyHost, proxyPort);
        }
        client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        client.getHttpConnectionManager().getParams().setSoTimeout(120000);
        StringBuilder sb = new StringBuilder();
        GetMethod method = new GetMethod(tempurl);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        method.getParams().setContentCharset(charSet);
        method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        method.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        method.setRequestHeader("Connection", "close");
        if (StringUtils.isNotEmpty(XRequestedWith)) {
            method.setRequestHeader("X-Requested-With", XRequestedWith);
        }
        if (StringUtils.isNotEmpty(userAgent)) {
            method.setRequestHeader("User-Agent", userAgent);
        } else {
            method.setRequestHeader("User-Agent",
                    "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:20.0) Gecko/20100101 Firefox/20.0");
        }
        if (StringUtils.isNotEmpty(cookie)) {
            method.setRequestHeader("Cookie", cookie);
        }

        if (StringUtils.isNotEmpty(referer)) {
            method.setRequestHeader("Referer", referer);
        }

        try {
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        charSet));
                String tmp = null;
                while ((tmp = reader.readLine()) != null) {
                    sb.append(tmp);
                    sb.append("\r\n");
                }
            } else {
                System.err.println("Response Code: " + statusCode);
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return sb.toString();
    }

    public static boolean downloadFile(String url, String path) {
        if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(path)) {
            FileOutputStream output = null;
            GetMethod method = null;
            try {
                URL u = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                String realUrl = conn.getURL().toString();
                conn.disconnect();
                HttpClient client = new HttpClient();
                client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
                client.getHttpConnectionManager().getParams().setSoTimeout(600000);
                method = new GetMethod(realUrl);
                client.executeMethod(method);
                StatusLine statusLine = method.getStatusLine();
                if (statusLine != null && statusLine.getStatusCode() == 200) {
                    output = new FileOutputStream(path);
                    output.write(method.getResponseBody());
                    output.close();
                    return Boolean.TRUE;
                }
            } catch (HttpException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            } catch (Exception e){
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
            finally {
                if (method != null) {
                    method.releaseConnection();
                }
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 获取无参数链接
     *
     * @param url
     *            链接
     * @return
     */
    public static String getUrlWithoutParams(String url) {
        if (StringUtils.isNotEmpty(url)) {
            int index = url.indexOf("?");
            if (index > 0) {
                return url.substring(0, index);
            }

            return url;
        }
        return String.valueOf("");
    }

    /**
     * 文件上传
     *
     * @param tempurl
     *            链接
     * @param parts
     *            post参数
     * @return
     */
    public static String fileUpload(String tempurl, Part[] parts) {
        return fileUpload(tempurl, null, parts);
    }

    public static String fileUpload(String tempurl, Map<String, String> params, Part[] parts) {
        StringBuilder sb = new StringBuilder();
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        client.getHttpConnectionManager().getParams().setSoTimeout(120000);
        PostMethod method = new PostMethod(tempurl);
        List<Part> req = new ArrayList<Part>();
        if (CollectionUtils.isNotEmpty(params)) {
            for(Map.Entry<String, String> entry:params.entrySet()){
                req.add(new StringPart(entry.getKey(),entry.getValue()));
            }
        }
        req.addAll(Arrays.asList(parts));
        try {
            MultipartRequestEntity entity = new MultipartRequestEntity(req.toArray(new Part[]{}), method.getParams());
            method.setRequestEntity(entity);
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        "utf-8"));
                String tmp = null;
                while ((tmp = reader.readLine()) != null) {
                    sb.append(tmp);
                    sb.append("\r\n");
                }
            } else {
                System.err.println("Response Code: " + statusCode);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return sb.toString();
    }

    public static String post(String url, Map<String, String> params, String xml) {
        String returnData = "";
        if (StringUtils.isNotEmpty(url)) {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(url);
            try {
                if (CollectionUtils.isNotEmpty(params)) {
                    method.getParams().setContentCharset(ConstUtils.Server.CHARSET);
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        method.setParameter(entry.getKey(), entry.getValue());
                    }
                }
                if (StringUtils.isNotEmpty(xml)) {
                    method.setRequestEntity(new StringRequestEntity(xml, "text/xml", ConstUtils.Server.CHARSET));
                }
                int statusCode = client.executeMethod(method);
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        ConstUtils.Server.CHARSET));
                StringBuilder sb = new StringBuilder();
                String tmp = null;
                while ((tmp = reader.readLine()) != null) {
                    sb.append(tmp);
                    sb.append("\r\n");
                }
                if (statusCode == HttpStatus.SC_OK) {
                    returnData = sb.toString();
                } else {
                    StringBuilder message = new StringBuilder();
                    message.append("http post has an error, Response Code:");
                    message.append(statusCode);
                    message.append("url:"+url+" param "+params.toString());
                    message.append(" Response content: ");
                    message.append(sb.toString());
                    LOGGER.error(message.toString());
                }
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                method.releaseConnection();
            }
        }
        return returnData;
    }

    public static String post(String url, Map<String, String> params) {
        return post(url, params, null);
    }

    public static String post(String url, String xml) {
        return post(url, null, xml);
    }

    /**
     * 获取完整的url
     *
     * @param request
     * @return
     */
    public static String getFullUrl(HttpServletRequest request) {
        StringBuffer buff = request.getRequestURL();
        String query = request.getQueryString();
        if (StringUtils.isNotEmpty(query)) {
            buff.append("?");
            buff.append(query);
        }

        return WebUtils.escapeHtml(WebUtils.unescapeHtml(buff.toString()));
    }

    /**
     * url转码处理
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        if (StringUtils.isNotEmpty(url)) {
            try {
                return URLEncoder.encode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }

        return url;
    }

    /**
     * url解码处理
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        if (StringUtils.isNotEmpty(url)) {
            try {
                return URLDecoder.decode(url, "UTF-8");

            } catch (UnsupportedEncodingException e) {
            }
        }

        return url;
    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * chrome agent代理
     */
    public static final String CHROME_AGENT = "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.94 Safari/537.36";
}

