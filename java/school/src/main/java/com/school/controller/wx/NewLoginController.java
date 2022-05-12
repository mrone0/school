package com.school.controller.wx;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.config.LocalDateTimeSerializerConfig;
import com.school.entity.Weixin;
import com.school.mapper.WeixinMapper;
import com.school.service.impl.WxService;
import com.school.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class NewLoginController {

    LocalDateTimeSerializerConfig config = new LocalDateTimeSerializerConfig();


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    WeixinMapper weixinMapper;

    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    WxService wxService;
    String redisKey = "";

    String AppId = "wx476cab07cfe094df";  //公众平台自己的appId
    String AppSecret = "216084f0d7fd33b0cf1976afe8823023";  //AppSecret

//
//    @RequestMapping("/gettoken")
//    public String wxToken(@RequestParam("code") String code){
//        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
//                "appid=" + AppId +
//                "&secret=" + AppSecret +
//                "&js_code=" + code +
//                "&grant_type=authorization_code";
//        String jsonData = restTemplate.getForObject(url, String.class);
//        JSONObject jsonObject = JSONObject.parseObject(jsonData);
//        if (StringUtils.contains(jsonData, "errcode")) {
//            //出错了
//
//            return "err";
//        }
//        String openid = jsonObject.getString("openid");
//        String sessionKey = jsonObject.getString("session_key");
//        Weixin weixin = new Weixin();
//        QueryWrapper<Weixin> wrapper = new QueryWrapper();
//        wrapper.eq("open_id",openid);
//        Weixin weixin1 = weixinMapper.selectOne(wrapper);
//        if (weixin1==null){
//            String md5key = "MRONE" + DigestUtils.md5Hex(jsonData + "MRONE_WX_LOGIN");
//            //校验通过 规则:WX_LOGIN_(MD5值)
//            redisKey = "WX_LOGIN" + md5key;
//            redisTemplate.opsForValue().set(redisKey, jsonData, Duration.ofDays(1));  //存到redis 1天
//            String token = this.jwtUtil.getToken(openid,sessionKey);
//            return token;
//        }else {
//
//            return "登录";
//        }
//    }


    @RequestMapping("/login")
    public Result<Object> wxLogin(@RequestParam("code") String code, String encryptedData, String iv) throws Exception {
        Result<Object> result = new Result();
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + AppId +
                "&secret=" + AppSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        String jsonData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        if (StringUtils.contains(jsonData, "errcode")) {
            //出错了
            result.setMessage("err");
            return result;
        }
        String md5key = "MRONE" + DigestUtils.md5Hex(jsonData + "MRONE_WX_LOGIN");
        //校验通过 规则:WX_LOGIN_(MD5值)
        redisKey = "WX_LOGIN" + md5key;
        redisTemplate.opsForValue().set(redisKey, jsonData, Duration.ofDays(1));  //存到redis 1天
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        Weixin weixin = new Weixin();
        QueryWrapper<Weixin> wrapper = new QueryWrapper();
        wrapper.select("open_id").eq("open_id", openid);
        Weixin weixin1 = weixinMapper.selectOne(wrapper);
         String  s = wxService.wxDecrypt(encryptedData, jsonObject, iv);
        JSONObject jsonObject1 = JSON.parseObject(s);
        Object watermark = jsonObject1.get("watermark");
        JSONObject jsonObject2 = JSON.parseObject(String.valueOf(watermark));
        if (weixin1 == null) {
            weixin.setOpenId(openid);
            weixin.setSessionkey(sessionKey);
                jsonObject2.get("timestamp");
                jsonObject2.get("appid");
                weixin.setNickName(String.valueOf(jsonObject1.get("nickName")));
                weixin.setAvatarUrl(String.valueOf(jsonObject1.get("avatarUrl")));
                String timestamp = String.valueOf(jsonObject2.get("timestamp"));
                Date localDateTime = config.localDateTime(timestamp);
                weixin.setCreateTime(localDateTime);
                weixin.setAppId(String.valueOf(jsonObject2.get("appid")));
                weixinMapper.insert(weixin);
            String token = jwtUtil.getToken(openid, sessionKey);
            result.setData(token);
            return result;
            }else {
            String timestamp = String.valueOf(jsonObject2.get("timestamp"));
            Date localDateTime = config.localDateTime(timestamp);
            weixin.setLoginTime(localDateTime);
            QueryWrapper<Weixin> wrapper1= new QueryWrapper();
            wrapper1.select("login_time").eq("open_id",openid);
            weixinMapper.update(weixin,wrapper1);
            result.setData(openid);
            String token = jwtUtil.getToken(openid, sessionKey);
            result.setData(token);
        }
        return result;
    }
}
