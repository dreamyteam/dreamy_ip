package com.dreamy.crawler;

import com.dreamy.utils.HttpUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by wangyongxing on 16/4/18.
 */
public class SoMain {
    public static void main1(String[] args) throws UnsupportedEncodingException {
        String q="大主宰";
        String area="全国";
       String aa= java.net.URLEncoder.encode(q,"UTF-8");
        System.out.println(aa);

        q=HttpUtils.encodeUrl(q);
        area=HttpUtils.encodeUrl(area);
        String url="http://index.so.com/index.php?a=overviewJson&q="+q+"&area="+area;
        System.out.println(url);
        String json= HttpUtils.getHtmlGet(url);
        System.out.println(json);
    }


    public static void main111(String[] args) throws UnsupportedEncodingException {
        String q="大主宰";
        String area="全国";
        String aa=java.net.URLEncoder.encode(q,"UTF-8");
        String url="http://index.so.com/index.php?a=portrayalJson&t=30&q="+aa;
        String json= HttpUtils.getHtmlGet(url);
        String gbkStr = decodeUnicode(json);
        System.out.println(gbkStr);



    }

    public static void main(String[] args) {
        long time=System.currentTimeMillis();
        String url="http://data.weibo.com/index/ajax/getdefaultattributealldata?__rnd="+time;
        String json= HttpUtils.getHtmlGet(url,"utf-8","",0,"INAGLOBAL=2667795442976.0576.1459828210974; SUB=_2AkMgTr-Mf8NhqwJRmPwdz2LiZIp_ywDEiebDAHzsJxJjHn4S7T9lqCQ1L4QiewKNHa5VdZdAMCBn__4U2A..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55z29P9D9WFLaLyNPNYoIOERKsv2sJjS; DATA=usrmdinst_15; _s_tentry=-; Apache=1826308714225.8882.1460950460770; ULV=1460950460783:3:3:1:1826308714225.8882.1460950460770:1460621842520; WBStore=97e433e7cc20168d|undefined; PHPSESSID=uv4uc0717fosng4us86fk53gs1","","","");
        System.out.println(json);
    }

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public static  String chinaToUnicode(String str){
        String result="";
        for (int i = 0; i < str.length(); i++){
            int chr1 = (char) str.charAt(i);
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
                result+="\\u" + Integer.toHexString(chr1);
            }else{
                result+=str.charAt(i);
            }
        }
        return result;
    }
}
