package com.licc.cat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.licc.cat.entity.ApiLog;
import com.licc.cat.mapper.ApiLogMapper;
import com.licc.cat.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李臣臣
 * @Date: 2020/08/12 0012 13:17
 * @Description: 调用微信统一
 */
@Service
@Slf4j
public class WeChatHttpInterface {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ApiLogMapper apiLogMapper;


    /**
     * @author: 李臣臣
     * @Date: 2020/08/12 0012 13:20
     * @Description: 微信 post请求集合类
     */
    public Result sendRequest(String url, String method, Map map, String content) {

        if (redisUtil.hasKey(Constants.WeiXin_ALIAS.ACCESS_TOKEN)){
            String token = redisUtil.get(Constants.WeiXin_ALIAS.ACCESS_TOKEN).toString();
            url = url.replace("ACCESS_TOKEN", token);
        }

        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> querys = new HashMap<String, String>();

        HttpResponse response = null;
        try {

            if (Constants.METHOD.POST.equals(method)) {
                response = HttpUtils.doPost(url, headers, querys, map);
            } else {
                response = HttpUtils.doGet(url, headers, querys);
            }

            String rep = EntityUtils.toString(response.getEntity());
            //获取response的body
            JSONObject result1 = new JSONObject(JSON.parseObject(rep));

            log.debug("微信返回数据====={}", result1);


            ApiLog apiLog = new ApiLog();
            apiLog.setMethod(method);
            apiLog.setParam(map == null ? "" : map.toString());
            apiLog.setUrl(url);
            apiLog.setResult(result1.toJSONString());

            apiLog.setContent(content);

            try {

                apiLogMapper.insert(apiLog);
            } catch (Exception e) {
                log.error("日志入库失败");
                e.printStackTrace();
            }


            if ((!result1.containsKey("errcode")) || result1.getInteger("errcode") == 0) {

                return Result.build().setData(result1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error();

    }


}