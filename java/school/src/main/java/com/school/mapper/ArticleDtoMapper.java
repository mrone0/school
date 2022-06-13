package com.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.dto.ArticleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDtoMapper extends BaseMapper<ArticleDto> {
    Page<ArticleDto> findArticleByTag(Page<ArticleDto> page,String tag);

    ArticleDto findArticleById(int id);

    Page<ArticleDto> findArticlesByOpenid(Page<ArticleDto> page,String openid);

    Page<ArticleDto> findUserArticlesByOpenid(Page<ArticleDto> page,String openid);

}
