package com.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.school.entity.Token;
import com.school.mapper.TokenMapper;
import com.school.service.TokenService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token>
    implements TokenService {

}




