package com.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.dto.CommentDto;
import com.school.mapper.CommentDtoMapper;
import com.school.service.CommentDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDtoServiceImpl extends ServiceImpl<CommentDtoMapper, CommentDto> implements CommentDtoService {

   @Autowired
   CommentDtoMapper commentDtoMapper;
    @Override
    public List<CommentDto> findCommentById(int aid) {
        return commentDtoMapper.findCommentById(aid);
    }

    @Override
    public List<CommentDto> findUserCommentByOpenid(String openid) {
        return commentDtoMapper.findUserCommentByOpenid(openid);
    }

}
