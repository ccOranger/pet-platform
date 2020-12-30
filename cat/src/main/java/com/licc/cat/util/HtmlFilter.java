package com.licc.cat.util;


import org.apache.commons.lang3.StringUtils;

public class HtmlFilter {
    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";
    public static final String EMPTY = "";
    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";
    private static final char[][] TEXT = new char[64][];
    private static final char[][] TEXT_OBJ = new char[64][];

    static {
        for (int i = 0; i < 64; ++i) {
            TEXT[i] = new char[]{(char) i};
            TEXT_OBJ[i] = new char[]{(char) i};
        }

        TEXT[39] = "&#039;".toCharArray();
        TEXT[34] = "&quot;".toCharArray();
        TEXT[38] = "&amp".toCharArray();
        TEXT[60] = "&lt;".toCharArray();
        TEXT[62] = "&gt;".toCharArray();
        TEXT[47] = "&#47;".toCharArray();
        TEXT_OBJ[39] = "&#039;".toCharArray();
        TEXT_OBJ[38] = "&amp".toCharArray();
        TEXT_OBJ[60] = "&lt;".toCharArray();
        TEXT_OBJ[62] = "&gt;".toCharArray();
        TEXT_OBJ[47] = "&#47;".toCharArray();
    }

    public HtmlFilter() {
    }

    public static String restoreEscaped(String htmlStr) {
        return StringUtils.isBlank(htmlStr) ? htmlStr : htmlStr.replace("&#39;", "'").replace("&#47;", "/").replace("&lt;", "<").replace("&gt;", ">").replace("&amp", "&").replace("&quot;", "\"").replace("&#039;", "'");
    }

    public static String transcoding(String text) {
        return encode(text, TEXT);
    }

    public static String transcodingByOBJ(String text) {
        return encode(text, TEXT_OBJ);
    }

    private static String encode(String text) {
        return encode(text, TEXT);
    }

    public static String cleanHtmlTag(String content) {
        return content.replaceAll("(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)", "");
    }

    private static String encode(String text, char[][] array) {
        int len;
        if (text != null && (len = text.length()) != 0) {
            StringBuilder buffer = new StringBuilder(len + (len >> 2));

            for (int i = 0; i < len; ++i) {
                char c = text.charAt(i);
                if (c < '@') {
                    buffer.append(array[c]);
                } else {
                    buffer.append(c);
                }
            }

            return buffer.toString();
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println("=====<a></a><script>alert(\"前置通知开始\");</script>=====");
        System.out.println(encode("=====<a></a><script>alert(\"前置通知开始\");</script>====="));
        String str = encode("http://test.rayeye.cn:808 8/workspace /myWorkspace.do?projectId=44#1306");
        System.out.println("编码：" + str);
        System.out.println("解码：" + restoreEscaped(str));
    }
}
