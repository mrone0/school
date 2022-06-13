package com.school.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.commons.Result;
import com.school.commons.ResultCode;
import com.school.dto.ArticleDto;
import com.school.dto.CommentDto;
import com.school.entity.Article;
import com.school.entity.Comment;
import com.school.entity.Star;
import com.school.entity.User;
import com.school.mapper.ArticleMapper;
import com.school.mapper.CommentMapper;
import com.school.mapper.StarMapper;
import com.school.mapper.UserMapper;
import com.school.service.ArticleDtoService;
import com.school.service.CommentDtoService;
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
public class UserController {
    private static int B;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleDtoService articleDtoService;

    @Autowired
    StarMapper starMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentDtoService commentDtoService;


    String redisKey = "WX_LOGIN";

    @RequestMapping("/userinfo")
    public Result<Object> user(User user, HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        Result<Object> result = new Result();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        if (openid != null) {
            if (userMapper.selectOne(wrapper) == null) {
                userMapper.insert(user);
                result.setCode(ResultCode.SUCCESS.code());
                return result;
            } else if (!user.getUsername().equals(userMapper.selectOne(wrapper).getUsername())) {
                userMapper.insert(user);
                result.setCode(ResultCode.RESULT_DATA_NONE.code());
                return result;
            } else {
                result.setCode(ResultCode.USER_NOT_EXIST.code());
                return result;
            }
        } else {
            result.setCode(ResultCode.PERMISSION_TOKEN_EXPIRED.code());
            return result;
        }
    }

    @RequestMapping("/user/fabu")
    public Page<ArticleDto> faBu(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        if (openid != null) {
            Page<ArticleDto> list = articleDtoService.findUserArticlesByOpenid(page,openid);
            return list;
        } else {
            return null;
        }

    }

    @RequestMapping("/user/star")
    public Page<ArticleDto> userStar(HttpServletRequest request,Long current) {
        Page<ArticleDto> page = new Page<>(current,10);
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        QueryWrapper<Star> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        List<Star> stars = starMapper.selectList(wrapper);
        if (openid != null && stars != null) {
            Page<ArticleDto> list = articleDtoService.findArticlesByOpenid(page,openid);
            return list;
        } else {
            return null;
        }
    }


    @RequestMapping("/user/comment")
    public List<CommentDto> userComment(HttpServletRequest request) {
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            wrapper.eq("openid", openid);
            List<CommentDto> comments = commentDtoService.findUserCommentByOpenid(openid);
            return comments;
        } else {
            return null;
        }
    }

    @RequestMapping("/user/fabu/delete")
    public Result<Object> userDelete(HttpServletRequest request, int id) {
        Result<Object> result = new Result<>();
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        QueryWrapper<Article> id1 = wrapper.eq("id", id);
        QueryWrapper<Star> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Star> id2 = queryWrapper.eq("id", id);
        if (openid != null && id1 != null&&id2!=null) {
            articleMapper.delete(wrapper);
            starMapper.delete(queryWrapper);
            result.setCode(ResultCode.SUCCESS.code());
            return result;
        } else {
            result.setCode(ResultCode.FAIL.code());
            return result;
        }
    }


    @RequestMapping("/user/comment/del")
    public Result<Object> userCommentDelete(HttpServletRequest request, int id, int aid) {
        Result<Object> result = new Result<>();
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        if (openid != null) {
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            QueryWrapper<Comment> eq = wrapper.eq("id", id).eq("aid", aid);
            if (eq != null) {
                commentMapper.delete(wrapper);
                result.setCode(ResultCode.SUCCESS.code());
                return result;
            } else {
                result.setCode(ResultCode.PARAM_IS_BLANK.code());
                return result;
            }
        } else {
            result.setCode(ResultCode.PERMISSION_TOKEN_EXPIRED.code());
            return result;
        }

    }
}

