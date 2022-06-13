package com.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.entity.Star;

import java.util.List;

/**
 * @Entity generator.domain.Star
 */
public interface StarMapper extends BaseMapper<Star> {
    List<Integer> findAidList(String openid);
}




