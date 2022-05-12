package com.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.school.entity.Comment;
import com.school.mapper.CommentMapper;
import com.school.service.CommentService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

}




