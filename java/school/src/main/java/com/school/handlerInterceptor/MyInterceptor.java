package com.school.handlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.ResultCode;
import com.school.entity.Weixin;
import com.school.mapper.WeixinMapper;
import com.school.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    WeixinMapper weixinMapper;

    @Autowired
    private JwtUtil tokenObj;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检查有没有需要用户权限的注解
        //如果有注解Authorize，就需要验证token

        String token = request.getHeader("authorization");// 从 http 请求头中取出 token
        // 执行认证
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 获取 token 中的 open id
        String openid;
        try {
            // 获取 openid
            token = String.valueOf(tokenObj.getTokenClaim(token));
            token = token.replace("=", ":");
            JSONObject jsonObject = JSONObject.parseObject(token);
            String sub = jsonObject.getString("sub");
            JSONObject jsonObject1 = JSONObject.parseObject(sub);
            openid = jsonObject1.getString("openid");
            // 添加request参数，用于传递openid
            request.setAttribute("openid", openid);
            // 根据openId 查询用户信息
            QueryWrapper<Weixin> wrapper = new QueryWrapper<>();
            wrapper.eq("open_id", openid);
            Weixin weixin = weixinMapper.selectOne(wrapper);
            if (weixin == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }else {
                request.setAttribute("openid",weixin.getOpenId());
            }

//            try {
//                String openid1 = JWT.decode(token).getClaim("openid").as(String.class);
//                // 添加request参数，用于传递openid
//                request.setAttribute("openid", openid1);
//            } catch (Exception e) {
//            }
//
//            // 验证 openid
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(weixin.getOpenId())).build();
//            try {
//                jwtVerifier.verify(token);
//            } catch (JWTVerificationException e) {
//                throw new RuntimeException("401");
//            }
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }

            return true;
        }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    //拦截器：请求之后：afterCompletion
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }


}

