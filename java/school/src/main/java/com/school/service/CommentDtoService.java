package com.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.dto.CommentDto;

import java.util.List;

public interface CommentDtoService extends IService<CommentDto> {
    List<CommentDto> findCommentById(int aid);

    List<CommentDto> findUserCommentByOpenid(String openid);

}
