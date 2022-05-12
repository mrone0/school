package com.school.controller.funtions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.entity.Article;
import com.school.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Controller
@RequestMapping("/wx")
public class TaoLunController {
    @Autowired
    ArticleMapper articleMapper;
    @RequestMapping("/taolun")
    public List<Article> ErShou(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("tag","讨论");
        List<Article> list = articleMapper.selectList(wrapper);
        return list;
    }
}
