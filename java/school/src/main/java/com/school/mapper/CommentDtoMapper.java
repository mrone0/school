package com.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDtoMapper extends BaseMapper<CommentDto> {
    List<CommentDto> findCommentById(int aid);

    List<CommentDto> findUserCommentByOpenid(String openid);

}
