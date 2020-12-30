package com.licc.cat.controller;

import com.alibaba.fastjson.JSONObject;
import com.licc.cat.redis.RedisUtil;
import com.licc.cat.util.Constants;
import com.licc.cat.util.Result;
import com.licc.cat.util.ResultCode;
import com.licc.cat.util.WeChatHttpInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @package: com.licc.cat.controller
 * @className: ${TYPE_NAME}
 * @description: 定时任务
 * @author: 李臣臣
 * @createDate: 2019/9/23 17:17
 * @updateUser: 李臣臣
 * @updateDate: 2019/9/23 17:17
 * @updateRemark: The modified content
 * @version: 1.0
 * <p>copyright: Copyright (c) 2019</p>
 */
@Api(value = "WeChatTask")
@RestController
@RequestMapping(value = "/api/wechat")
@Slf4j
public class WeChatTask implements InitializingBean {

    @Value("${weChat.appID}")
    private String appID;

    @Value("${weChat.appSecret}")
    private String appSecret;

    @Autowired
    private WeChatHttpInterface weChatHttpInterface;


    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/getAccessToken")
    @ApiOperation(value = "每一个小时获取AccessToken，并存redis")
    @Scheduled(cron = "0 0 0/1 * * ?")
    public Result getAccessToken() {

        String url = Constants.WeiXin_ALIAS.ACCESS_TOKEN_URL.replace("APPID", appID).replace("APPSECRET", appSecret);
        Result result = weChatHttpInterface.sendRequest(url, Constants.METHOD.GET, null, Constants.WeiXin_ALIAS.ACCESS_TOKEN_URL_CONTENT);
        if (result.getCode() == ResultCode.HTTP_200) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result.getData());
            log.debug("获取微信小程序授权码成功,[access_token={}]", jsonObject.getString("access_token"));
            redisUtil.set(Constants.WeiXin_ALIAS.ACCESS_TOKEN, jsonObject.getString("access_token"), Constants.WeiXin_ALIAS.DEFAULT_ACCESS_TOKEN_TIME_OUT_SECS);
        }

        return Result.build();
    }


    /**
     * @author: 李臣臣
     * @Date: 2020/08/07 0007 13:19
     * @Description: 服务启动执行一次
     */
    @Override
    public void afterPropertiesSet() {
        this.getAccessToken();
    }

}
