package com.school.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.school.config.LocalDateTimeSerializerConfig;
import com.school.dto.TemplateData;
import com.school.entity.*;
import com.school.dto.WxMssVO;
import com.school.mapper.MessagepushMapper;
import com.school.mapper.WeixinMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ResponseBody
@Controller
@RequestMapping("/wx")
public class CheckSignatureController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    String redisKey = "";


    @Autowired
    WeixinMapper weixinMapper;

    @Autowired
    MessagepushMapper messagepushMapper;

    LocalDateTimeSerializerConfig datetime = new LocalDateTimeSerializerConfig();


    String AppId = "";  //公众平台自己的appId
    String AppSecret = "";  //AppSecret


    @RequestMapping("/token")
    public String Token() {
        Map<Object, Object> result = new HashMap<>();
        result.put("status", 200);
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential" +
                "&appid=" + AppId +
                "&secret=" + AppSecret;
        String token = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(token);
        String accessToken = jsonObject.getString("access_token");
//        Token token1 = new Token();
        if (StringUtils.isEmpty(accessToken)) {
            System.out.println(("获取access token失败: {}" + jsonObject.getString("errmsg")));
            throw new ApiException("获取access token失败");
        } else {
//            token1.setAccesstoken(accessToken);
            redisKey = "token";
//            int insert = tokenMapper.insert(token1);
            redisTemplate.opsForValue().set(redisKey, accessToken, Duration.ofHours(2));
            return accessToken;
        }
    }

    @RequestMapping("/push")
    public String push(HttpServletRequest request) throws ParseException {
        Object openid = request.getAttribute("openid");
        Messagepush messagepush = messagepushMapper.selectById(1L);
        String token = (String) redisTemplate.opsForValue().get(redisKey);
        Weixin weixin = new Weixin();
        QueryWrapper<Weixin> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openid);
        Weixin weixin1 = weixinMapper.selectOne(wrapper);
        if (weixin1 == null) {
            return "null";
        } else {
            String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token;
            //拼接推送消息
            WxMssVO wxMssVO = new WxMssVO();
            wxMssVO.setTouser(String.valueOf(openid));//open id
            wxMssVO.setTemplate_id(messagepush.getTemplateId());//模板id
            wxMssVO.setPage("pages/index/index");

            Date date = new Date();
            Map<String, TemplateData> m = new HashMap<>(5);
            m.put("thing2", new TemplateData(messagepush.getTitle()));
            m.put("date3", new TemplateData(datetime.GeLin(date)));
            m.put("thing10", new TemplateData(messagepush.getAddress()));
            m.put("thing12", new TemplateData(messagepush.getSponsor()));
            m.put("thing4", new TemplateData(messagepush.getDescription()));
            wxMssVO.setData(m);
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(url, wxMssVO, String.class);
            return responseEntity.getBody();

        }


    }

    @GetMapping("/dingyue")
    public String GZH() {
        String token = (String) redisTemplate.opsForValue().get(redisKey);
        String url = " https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + token;
        String industry_id1 = "39";
        String industry_id2 = "39";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                url,
                industry_id1,
                String.class,
                industry_id2);
        return responseEntity.toString();
    }

}
