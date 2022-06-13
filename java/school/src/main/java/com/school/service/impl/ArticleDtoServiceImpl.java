package com.school.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.dto.ArticleDto;
import com.school.mapper.ArticleDtoMapper;
import com.school.service.ArticleDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleDtoServiceImpl extends ServiceImpl<ArticleDtoMapper,ArticleDto> implements ArticleDtoService{
    @Autowired
    ArticleDtoMapper articleDtoMapper;


    @Override
    public Page<ArticleDto> findArticleByTag(Page<ArticleDto> page, String tag) {
        return articleDtoMapper.findArticleByTag(page,tag);
    }

    @Override
    public ArticleDto findArticleById(int id) {
        return articleDtoMapper.findArticleById(id);
    }

    @Override
    public Page<ArticleDto> findArticlesByOpenid(Page<ArticleDto> page, String openid) {
        return articleDtoMapper.findArticlesByOpenid(page,openid);
    }

    @Override
    public Page<ArticleDto> findUserArticlesByOpenid(Page<ArticleDto> page, String openid) {
        return articleDtoMapper.findUserArticlesByOpenid(page,openid);
    }
}
