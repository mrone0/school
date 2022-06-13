package com.school.controller.funtions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.commons.ResultCode;
import com.school.entity.Article;
import com.school.entity.Weixin;
import com.school.mapper.ArticleMapper;
import com.school.mapper.WeixinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class ArticleController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    WeixinMapper weixinMapper;


    @RequestMapping("/article")
    public Result<Object> article(Article article, HttpServletRequest request) {
        Result<Object> res = new Result();
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        QueryWrapper<Weixin> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openid);
        Weixin weixin = weixinMapper.selectOne(wrapper);
        if (weixin != null) {
            article.setOpenid((String) openid);
            Date date = new Date();
            article.setCreatetime(date);
            int insert = articleMapper.insert(article);
            if (insert == 1) {
                res.setCode(200);
                res.setMessage("发布成功");
                return res;
            } else {
                res.setCode(500);
                res.setMessage("未登录");
                return res;
            }
        } else {
            res.setCode(ResultCode.PERMISSION_TOKEN_EXPIRED.code());
            return res;
        }
    }


}
