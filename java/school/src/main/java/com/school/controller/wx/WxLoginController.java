package com.school.controller.wx;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.config.LocalDateTimeSerializerConfig;
import com.school.entity.Weixin;
import com.school.mapper.LogintimeMapper;
import com.school.mapper.WeixinMapper;
import com.school.service.impl.WxService;
import com.school.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx1")
public class WxLoginController {

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

    @PostMapping("login")
    public Map<String, Object> wxLogin(@RequestParam("code") String code) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + AppId +
                "&secret=" + AppSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
//        String jsonData = this.restTemplate.getForObject(url,String.class);
        String jsonData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        if (StringUtils.contains(jsonData, "errcode")) {
            //出错了
            result.put("status", 500);
            result.put("msg", "登录失败");
            return result;
        }
        String md5key = "MRONE" + DigestUtils.md5Hex(jsonData + "MRONE_WX_LOGIN");
        //校验通过 规则:WX_LOGIN_(MD5值)
        redisKey = "WX_LOGIN" + md5key;
        redisTemplate.opsForValue().set(redisKey, jsonData, Duration.ofDays(1));  //存到redis 1天
        String openid = jsonObject.getString("openid");
        result.put("openid", openid); //session_key和openid不应该返回到客户端
        return result;
    }


//    @PostMapping("/login")
//    public Map<Object, Object> wxLogin(@RequestParam("code") String code){
//        Map<Object,Object> result = new HashMap<>();
////        result.put("status",200);
//        String url="https://api.weixin.qq.com/sns/jscode2session?" +
//                "appid="+AppId +
//                "&secret="+AppSecret +
//                "&js_code="+code +
//                "&grant_type=authorization_code";
////        String jsonData = this.restTemplate.getForObject(url,String.class);
//        String jsonData = restTemplate.getForObject(url, String.class);
//        JSONObject jsonObject = JSONObject.parseObject(jsonData);
//        if (StringUtils.contains(jsonData,"errcode")){
//            //出错了
//            result.put("status",500);
//            result.put("msg","登录失败");
//            return result;
//        }
//        String md5key="MRONE"+ DigestUtils.md5Hex(jsonData+"MRONE_WX_LOGIN");
//        //校验通过 规则:WX_LOGIN_(MD5值)
//         redisKey="WX_LOGIN"+md5key;
//        redisTemplate.opsForValue().set(redisKey,jsonData, Duration.ofDays(1));  //存到redis 1天
//        String openid = jsonObject.getString("openid");
//        String sessionKey = jsonObject.getString("session_key");
//        Weixin weixin = new Weixin();
//        QueryWrapper<Weixin> wrapper = new QueryWrapper();
//        wrapper.select("open_id").eq("open_id",openid);
//        Weixin weixin1 = weixinMapper.selectOne(wrapper);
//        if(weixin1==null){
//            weixin.setOpenId(openid);
//            weixin.setSessionkey(sessionKey);
//            weixinMapper.insert(weixin);
//            String token = this.jwtUtil.getToken(weixin,sessionKey);
//            result.put("token",token);
//            System.out.println(result);
//            return result;
//        }else {
//            String token = this.jwtUtil.getToken(weixin1,sessionKey);
//            result.put("token",token);
//            System.out.println(result);
//            return result;
//        }
//
//    }

    @GetMapping("/userinfo")
    public String info(String encryptedData, String iv) {
        String openId = "";
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        Weixin weixin = new Weixin();
        weixin.setOpenId(openid);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("open_id").eq("open_id", openid);
        Weixin weixin1 = weixinMapper.selectOne(wrapper);
        if (weixin1 != null) {
            openId = weixin1.getOpenId();
        }
        try {
            String s = null;
            s = wxService.wxDecrypt(encryptedData, jsonObject, iv);
            JSONObject jsonObject1 = JSON.parseObject(s);
            Object watermark = jsonObject1.get("watermark");
            JSONObject jsonObject2 = JSON.parseObject(String.valueOf(watermark));
            jsonObject2.get("timestamp");
            jsonObject2.get("appid");

            if (!openid.equals(openId)) {
                weixin.setNickName(String.valueOf(jsonObject1.get("nickName")));
                weixin.setAvatarUrl(String.valueOf(jsonObject1.get("avatarUrl")));
                String timestamp = String.valueOf(jsonObject2.get("timestamp"));
                Date localDateTime = config.localDateTime(timestamp);
                weixin.setCreateTime(localDateTime);
                weixin.setAppId(String.valueOf(jsonObject2.get("appid")));
                weixinMapper.insert(weixin);
            } else {
                String timestamp = String.valueOf(jsonObject2.get("timestamp"));
                Date localDateTime = config.localDateTime(timestamp);
//                weixin.setLoginTime(localDateTime);
                QueryWrapper wrapper1 = new QueryWrapper();
                wrapper1.select("login_time").eq("open_id", openid);
                weixinMapper.update(weixin, wrapper1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}



