package com.school.controller.funtions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.entity.Article;
import com.school.entity.Star;
import com.school.mapper.ArticleMapper;
import com.school.mapper.StarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/wx")
@Controller
@ResponseBody
public class StarController {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    StarMapper starMapper;


    @RequestMapping("/show")
    public Result<Object> show(int id, HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        Result<Object> result = new Result<>();
        Article article = articleMapper.selectById(id);
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        QueryWrapper<Star> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid).eq("aid", id);
        if (openid != null && article != null && starMapper.selectOne(wrapper) != null
        ) {
            result.setCode(1);
            return result;
        } else {
            result.setCode(0);
            return result;
        }
    }


    @RequestMapping("/star")
    public Result<Object> star(int id, HttpServletRequest request) {
        Result<Object> result = new Result();
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        Article article = articleMapper.selectById(id);
        QueryWrapper<Star> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid).eq("aid", id);
        Star star = starMapper.selectOne(wrapper);
        Star star1 = new Star();
        if (openid != null && article != null) {
            if (star != null && star.getAid() == id) {
                starMapper.delete(wrapper);
                result.setCode(0);
                return result;
            } else {
                star1.setOpenid(String.valueOf(openid));
                star1.setAid(id);
                starMapper.insert(star1);
                result.setCode(1);
                return result;
            }
        } else {
            return result;
        }
    }
}
