package com.school.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.entity.Article;
import com.school.entity.Star;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @Entity com.school.entity.Article
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




