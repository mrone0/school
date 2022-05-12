package com.school.util;

import com.alibaba.fastjson.JSONObject;
import com.school.entity.Weixin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

   private String secret= "NjYwQ0Y0N0M3ODhFODYwOUU2RDM1RTNDRkNENjQ1REE=";
   private long expire= 3600;
   private String header="token";
        public String getToken (String openid, String session_key){
            Date nowDate = new Date();
            //过期时间
            Date expireDate = new Date(nowDate.getTime() + expire * 100);
            Map<String,Object> maps = new HashMap<>();
            maps.put("openid",openid);
            maps.put("sessionKey",session_key);
            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(JSONObject.toJSONString(maps))
                    .setIssuedAt(nowDate)
                    .setExpiration(expireDate)
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        }
        /*
         * 获取 Token 中注册信息
         */
        public Claims getTokenClaim (String token) {
            try {
                return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

    }

