package com.school.controller.funtions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.dto.ArticleDto;
import com.school.service.ArticleDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class ArticleListController {
    @Autowired
    ArticleDtoService articleDtoService;

    @Autowired
    private RedisTemplate redisTemplate;



    @RequestMapping("/detail/article")
    public ArticleDto detail(int id,HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            ArticleDto articleById = articleDtoService.findArticleById(id);
            return articleById;
        } else {
            return null;
        }
    }

    @RequestMapping("/tree")
    public Page<ArticleDto> tree(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            Page<ArticleDto> article = articleDtoService.findArticleByTag(page,"情感");
            return article;
        } else {
            return null;
        }

    }

    @RequestMapping("/ershou")
    public Page<ArticleDto> erShou(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            Page<ArticleDto> list = articleDtoService.findArticleByTag(page,"二手");
            return list;
        } else {
            return null;
        }
    }

    @RequestMapping("/jianzhi")
    public Page<ArticleDto> jianZhi(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            Page<ArticleDto> list = articleDtoService.findArticleByTag(page,"兼职");
            return list;
        } else {
            return null;
        }
    }

    @RequestMapping("/taolun")
    public Page<ArticleDto> taoLun(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            Page<ArticleDto> list = articleDtoService.findArticleByTag(page,"讨论");
            return list;
        } else {
            return null;
        }
    }

    @RequestMapping("/yishi")
    public Page<ArticleDto> yiShi(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            Page<ArticleDto> list = articleDtoService.findArticleByTag(page,"失物招领");
            return list;
        } else {
            return null;
        }
    }

    @RequestMapping("/yiwen")
    public Page<ArticleDto> yiWen(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            Page<ArticleDto> list = articleDtoService.findArticleByTag(page,"疑问");
            return list;
        } else {
            return null;
        }
    }

}

