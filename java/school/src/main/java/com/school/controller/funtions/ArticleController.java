package com.school.controller.funtions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.entity.Article;
import com.school.entity.Weixin;
import com.school.mapper.ArticleMapper;
import com.school.mapper.WeixinMapper;
import com.school.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class ArticleController {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    WeixinMapper weixinMapper;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping("/article")
    public Result<Article> Article(Article article, HttpServletRequest request){
        System.out.println(article);
        QueryWrapper<Weixin> wrapper = new QueryWrapper<>();
        Object openid = request.getAttribute("openid");
        wrapper.eq("open_id", openid);
        Weixin weixin = weixinMapper.selectOne(wrapper);
        if(weixin!=null){
            article.setOpenid((String) openid);
            article.setAvatar(weixin.getAvatarUrl());
            article.setNickname(weixin.getNickName());
        }

        Date date = new Date();
        Result res = new Result();
        article.setCreatetime( date);
        int insert = articleMapper.insert(article);
        if(insert==1){
            res.setCode(200);
            res.setMessage("发布成功");
            return res;
        }else {
            res.setCode(500);
            res.setMessage("未登录");
            return res;
        }
    }



    @RequestMapping("/detail/article")
    public Article ErShouList(int id){
        Article article = articleMapper.selectById(id);
        return article;
    }


    @RequestMapping("/tree")
    public List<Article> Tree(){
    QueryWrapper wrapper = new QueryWrapper();
    wrapper.like("tag","情感");
    List list = articleMapper.selectList(wrapper);
        return list;
    }
}
