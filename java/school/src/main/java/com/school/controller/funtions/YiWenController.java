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
public class YiWenController {
    @Autowired
    ArticleMapper articleMapper;
    @RequestMapping("/yiwen")
    public List<Article> ErShou(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("*").like("tag","疑问");
        wrapper.or();
        wrapper.like("tag","其他");
        List<Article> list = articleMapper.selectList(wrapper);
        return list;
    }


}
