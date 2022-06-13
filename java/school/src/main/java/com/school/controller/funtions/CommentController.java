package com.school.controller.funtions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.commons.ResultCode;
import com.school.dto.CommentDto;
import com.school.entity.Article;
import com.school.entity.Comment;
import com.school.entity.Weixin;
import com.school.mapper.ArticleMapper;
import com.school.mapper.CommentMapper;
import com.school.mapper.WeixinMapper;
import com.school.service.CommentDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    WeixinMapper weixinMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CommentDtoService commentDtoService;


    @RequestMapping("/detail")
    public List<CommentDto> Comment(int aid) {
        Result<Object> result = new Result();
        List<CommentDto> comments = commentDtoService.findCommentById(aid);
        if (comments == null) {
            return null;
        } else {
            return comments;
        }
    }


    @RequestMapping("/add")
    public Result<Object> CommentAdd(int id, String comment, HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        Result<Object> result = new Result();
        QueryWrapper<Weixin> wrapper = new QueryWrapper();
        QueryWrapper<Article> wrapper2 = new QueryWrapper<>();
        if (openid != null) {
            Comment comment1 = new Comment();
            comment1.setOpenid(String.valueOf(openid));
            comment1.setAid(id);
            Date date = new Date();
            comment1.setTime(date);
            wrapper.eq("open_id", openid);
            Weixin weixin = weixinMapper.selectOne(wrapper);
            comment1.setComment(comment);
            commentMapper.insert(comment1);
            result.setCode(ResultCode.SUCCESS.code());
            result.setMessage(ResultCode.SUCCESS.message());
            return result;
        } else {
            result.setCode(ResultCode.FAIL.code());
            result.setMessage(ResultCode.FAIL.message());
            return result;
        }
    }
}
