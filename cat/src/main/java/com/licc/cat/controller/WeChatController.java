package com.licc.cat.controller;

import com.alibaba.fastjson.JSONObject;
import com.licc.cat.redis.RedisUtil;
import com.licc.cat.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 李臣臣
 * @Date: 2020/12/30 0030 17:12
 * @Description: 微信相关接口
 */
@Api(value = "WeChatTask")
@RestController
@RequestMapping(value = "/api/wechat")
@Slf4j
public class WeChatController {

    @Value("${weChat.appID}")
    private String appID;

    @Value("${weChat.appSecret}")
    private String appSecret;

    @Autowired
    private WeChatHttpInterface weChatHttpInterface;


    @Autowired
    private RedisUtil redisUtil;


    /**
     * @author: 李臣臣
     * @Date: 2020/12/30 0030 17:14
     * @Description: 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     */
    @GetMapping("/jscode2session")
    @ApiOperation(value = "登录凭证校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "临时登录凭证 code", paramType = "String"),
    })
    public Result jscode2session(String code) {

        String url = Constants.WeiXin_ALIAS.OAUTH_URL.replace("APPID", appID).replace("APPSECRET", appSecret).replace("JSCODE", code);
        Result result = weChatHttpInterface.sendRequest(url, Constants.METHOD.GET, null, Constants.WeiXin_ALIAS.OAUTH_URL_CONTENT);
        if (result.getCode() == ResultCode.HTTP_200) {

            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result.getData());


            //判断openid 在数据库中是否存在，存在的话直接返回登录信息


            redisUtil.set(Constants.WeiXin_ALIAS.AUTH_CODE + ":" + jsonObject.get("openid").toString(), jsonObject.get("session_key").toString(), Constants.WeiXin_ALIAS.DEFAULT_AUTH_CODE_TIME_OUT_SECS);

        }

        return Result.build();
    }


    @ApiOperation(value = "解密微信数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "openId", paramType = "String", required = true),
            @ApiImplicitParam(name = "encryptedData", value = "加密数据", paramType = "String", required = true),
            @ApiImplicitParam(name = "iv", value = "iv", paramType = "String", required = true)
    })
    @GetMapping("/get/encryptedData")
    public Result getEncryptedData(String openId, String encryptedData, String iv) {
        log.info("--------------解密微信数据-----------openId = " + openId + "-----encryptedData = " +
                " ---------" + encryptedData + "-------iv = " + iv);
        Object key = redisUtil.get(Constants.WeiXin_ALIAS.AUTH_CODE + ":" + openId);
        if (key == null) {
            return Result.error().setMessage("请重新登录或联系管理员");
        }
        String Session_key = key.toString();

        String encryptedData1 = HtmlFilter.restoreEscaped(encryptedData).toString();
        String iv1 = HtmlFilter.restoreEscaped(iv).toString();

        log.debug("encryptedData=====:" + encryptedData);
        log.debug("iv=====:" + iv);

        String data = null;
        try {
            data = WXBizDataCrypt.decrypt(encryptedData1, iv1, Session_key);
        } catch (Exception e) {
            log.error("解密失败==={}", e.getMessage(), e);
            e.printStackTrace();
            return Result.error().setMessage("解密失败").setData(JSONObject.parseObject(data));
        }
        log.debug("data=========" + data);

        return Result.build().setData(JSONObject.parseObject(data));
    }
}
