package com.school.controller.funtions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.school.entity.Active;
import com.school.entity.Daohang;
import com.school.entity.Functions;
import com.school.entity.Meishi;
import com.school.mapper.ActiveMapper;
import com.school.mapper.DaohangMapper;
import com.school.mapper.FunctionsMapper;
import com.school.mapper.MeishiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class FunctionController {
    @Autowired
    FunctionsMapper functionsMapper;


    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/function")
    public List<Functions> functions() {
            List<Functions> functions = functionsMapper.selectList(null);
            return functions;
    }

    @Autowired
    MeishiMapper meishiMapper;

    @RequestMapping("/meishi")
    public List<Meishi> MeiShi(HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            List<Meishi> meishis = meishiMapper.selectList(null);
            return meishis;
        } else {
            return null;
        }
    }


    @Autowired
    DaohangMapper daohangMapper;

    @RequestMapping("/daohang1")
    public List<Daohang> DaoHang(HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));

        if (openid != null) {
            List<Daohang> daohangs = daohangMapper.selectList(null);
            return daohangs;
        } else {
            return null;
        }
    }

    @Autowired
    ActiveMapper activeMapper;

    @RequestMapping("/active")
    public List<Active> active() {
            List<Active> actives = activeMapper.selectList(null);
            return actives;

    }
}
