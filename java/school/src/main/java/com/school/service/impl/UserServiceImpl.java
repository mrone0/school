package com.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.school.entity.User;
import com.school.mapper.UserMapper;
import com.school.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




