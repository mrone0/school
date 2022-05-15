package com.school.controller.funtions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.entity.Article;
import com.school.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class JianZhiController {
    @Autowired
    ArticleMapper articleMapper;
    @RequestMapping("/jianzhi")
    public List<Article> ErShou(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("tag","兼职");
        List<Article> list = articleMapper.selectList(wrapper);
        System.out.println(list);
        return list;
    }
}
