package com.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.school.entity.Article;
import com.school.mapper.ArticleMapper;
import com.school.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

}




