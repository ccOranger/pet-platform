package com.licc.cat.util;

/**
 * @author: 李臣臣
 * @Date: 2019/12/12 0012 10:47
 * @Description: 字段
 */
public class Constants {

    public static final String ENCODING = "UTF-8";


    public static final int DEFAULT_TIMEOUT = 5000;
    public static final long ACCESS_TOKEN_DEFAULT_TIMEOUT = 2 * 3600L;
    /***** 采用 session id  加密 ******/
    public final static String USER_ALIAS = "user-key";
    public static String ACCESS_TOKEN = "login:phone:";

    public static class METHOD {
        public static String POST = "POST";
        public static String GET = "GET";

    }

    /*** 微信相关文档接口 ***/
    public static class WeiXin_ALIAS {


        // 基础支持的accessToken
        public static String ACCESS_TOKEN = "auth:wecaht:access_token";

        //登录凭证校验
        public static String AUTH_CODE = "auth:wechat:code";
        public static Long DEFAULT_AUTH_CODE_TIME_OUT_SECS = 1 * 60L;


        // 基础支持的accessToken有效时间
        public static Long DEFAULT_ACCESS_TOKEN_TIME_OUT_SECS = 90 * 60L;


        // 获取小程序全局唯一后台接口调用凭据（access_token）
        public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        public static String ACCESS_TOKEN_URL_CONTENT = "获取小程序全局唯一后台接口调用凭据";

        //登录凭证校验
        public static String OAUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        public static String OAUTH_URL_CONTENT = "登录凭证校验";


    }

}
