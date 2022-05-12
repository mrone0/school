package com.school.controller.funtions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.commons.ResultCode;
import com.school.entity.Article;
import com.school.entity.Comment;
import com.school.entity.Weixin;
import com.school.mapper.ArticleMapper;
import com.school.mapper.CommentMapper;
import com.school.mapper.WeixinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ResponseBody
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    WeixinMapper weixinMapper;
    @Autowired
    ArticleMapper articleMapper;



    @RequestMapping("/detail")
    public Result<Object> Comment(int aid){
        Result<Object> result=new Result();
        QueryWrapper<Comment> wrapper=new QueryWrapper<>();
        wrapper.eq("aid",aid);
        List<Comment> comments = commentMapper.selectList(wrapper);
        if(comments==null){
            result.setMessage(String.valueOf(ResultCode.DATA_IS_WRONG));
            return result;
        }else {
            result.setData(comments);
            return result;
        }
    }


    @RequestMapping("/add")
    public Result<Object> CommentAdd(String openid, int id, String comments, String imageUrl){
        Result<Object> result = new Result();
        QueryWrapper<Weixin> wrapper = new QueryWrapper();
        QueryWrapper<Article> wrapper2=new QueryWrapper<>();
        if(wrapper2.eq("id",id)!=null&&wrapper.eq("open_id",openid)!=null){
            Comment comment = new Comment();
            comment.setOpenid(openid);
            comment.setAid(id);
            Date date=new Date();
            comment.setTime(date);
            wrapper.eq("open_id",openid);
            Weixin weixin = weixinMapper.selectOne(wrapper);
            comment.setAvatar(weixin.getAvatarUrl());
            comment.setNickname(weixin.getNickName());
            comment.setComment(comments);
            comment.setImage(imageUrl);
            commentMapper.insert(comment);
            result.setMessage(String.valueOf(ResultCode.SUCCESS));
            return result;
        }else {
            result.setMessage(String.valueOf(ResultCode.FAIL));
            return result;
        }
    }
}
