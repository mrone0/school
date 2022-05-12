package com.school.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.entity.Article;
import com.school.entity.User;
import com.school.mapper.ArticleMapper;
import com.school.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class UserController {
    private  static int B;
    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleMapper articleMapper;
    @RequestMapping("/userinfo")
    public Result User(User user){
        Result result = new Result();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        if(userMapper.selectOne(wrapper)==null){
            userMapper.insert(user);
            result.setCode(200);
        } else if(!user.getUsername().equals(userMapper.selectOne(wrapper).getUsername())){
            userMapper.insert(user);
            result.setCode(300);
        }else {
            result.setCode(404);
        }
        return result;
    }

    @RequestMapping("/user/fabu")
    public List<Article> FaBu(HttpServletRequest request){
        Object openid = request.getAttribute("openid");
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.eq("openid",openid);
        List list = articleMapper.selectList(wrapper);
        return list;
    }

}
