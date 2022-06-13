package com.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.dto.ArticleDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ArticleDtoService extends IService<ArticleDto> {
    Page<ArticleDto> findArticleByTag(Page<ArticleDto> page,String tag);

    ArticleDto findArticleById(int id);

    Page<ArticleDto> findArticlesByOpenid(Page<ArticleDto> page,String openid);

    Page<ArticleDto> findUserArticlesByOpenid(Page<ArticleDto> page,String openid);

}
