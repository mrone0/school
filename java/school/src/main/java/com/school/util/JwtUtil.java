package com.school.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

   private String secret= "NjYwQ0Y0N0M3ODhFODYwOUU2RDM1RTNDRkNENjQ1REE=";
   private long expire= 3600;
   private String header="token";
        public String getToken (String redisKey){
            Date nowDate = new Date();
            //过期时间
            Date expireDate = new Date(nowDate.getTime() + expire * 1000);
//            Map<String,Object> maps = new HashMap<>();
            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(redisKey)
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

