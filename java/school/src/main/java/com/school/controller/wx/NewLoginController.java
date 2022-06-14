package com.school.controller.wx;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.config.LocalDateTimeSerializerConfig;
import com.school.entity.Logintime;
import com.school.entity.Weixin;
import com.school.mapper.LogintimeMapper;
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


    @Autowired
    LogintimeMapper logintimeMapper;

    JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    WxService wxService;
    String redisKey = "";

    String AppId = "";  //公众平台自己的appId
    String AppSecret = "";  //AppSecret
    Date date = new Date();


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
        String md5key = DigestUtils.md5Hex(jsonData);
        //校验通过 规则:WX_LOGIN_(MD5值)
        redisKey = "WX_LOGIN" + md5key;
        redisTemplate.opsForValue().set(redisKey, jsonData, Duration.ofHours(1));//存到redis 1小时
        //每个用户有唯一的reidskey所以在每一次进行登录判断时，将rediskey发送出去
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        Weixin weixin = new Weixin();
        QueryWrapper<Weixin> wrapper = new QueryWrapper();
        wrapper.select("open_id").eq("open_id", openid);
        Weixin weixin1 = weixinMapper.selectOne(wrapper);
        String s = wxService.wxDecrypt(encryptedData, jsonObject, iv);
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
            String token = jwtUtil.getToken(redisKey);
            result.setData(token);
            result.setMessage(weixin.getAvatarUrl());
            result.setData2(weixin.getNickName());
            Logintime logintime =new Logintime();
            logintime.setLogintime(date);
            logintime.setOpenid(openid);
            logintimeMapper.insert(logintime);
            return result;
        } else {
            QueryWrapper<Weixin> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("open_id", openid);
            Weixin weixin2 = weixinMapper.selectOne(wrapper2);
            String token = jwtUtil.getToken(redisKey);
            result.setData(token);
            result.setData2(weixin2.getNickName());
            result.setMessage(weixin2.getAvatarUrl());
            return result;
        }
    }




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

}
