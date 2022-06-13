package com.school.controller.funtions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.commons.Result;
import com.school.commons.ResultCode;
import com.school.entity.Article;
import com.school.entity.Fabulous;
import com.school.mapper.ArticleMapper;
import com.school.mapper.FabulousMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
@RequestMapping("/wx")
public class ZanController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    FabulousMapper fabulousMapper;





    @RequestMapping("/get/zan")
    public Result<Object> getZan(int id,HttpServletRequest request){
        Object redisKey = request.getAttribute("redisKey");
        Result<Object> result = new Result<>();
        Article article = articleMapper.selectById(id);
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        QueryWrapper<Fabulous> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid).eq("aid",id);
        Fabulous fabulous = fabulousMapper.selectOne(wrapper);
        if (openid != null && article != null && fabulous!= null) {
            QueryWrapper<Fabulous> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("aid",id);
            Integer integer = fabulousMapper.selectCount(wrapper1);
            result.setCode(1);
            result.setData(integer);
            return result;
        } else {
            QueryWrapper<Fabulous> wrapper1 = new QueryWrapper<>();
            wrapper.eq("aid",id);
            Integer integer = fabulousMapper.selectCount(wrapper);
            result.setCode(0);
            result.setData(integer);
            return result;
        }
    }


    @RequestMapping("/zan")
    public Result<Object> zan(HttpServletRequest request,int id){
        Result<Object> result = new Result<>();
        Object redisKey = request.getAttribute("redisKey");
        String json = (String) redisTemplate.opsForValue().get(redisKey);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = String.valueOf(jsonObject.get("openid"));
        Article article = articleMapper.selectById(id);
        Fabulous fabulous = new Fabulous();
        QueryWrapper<Fabulous> wrapper = new QueryWrapper<>();
       wrapper.eq("openid", openid).eq("aid", id);
        Fabulous fabulous1 = fabulousMapper.selectOne(wrapper);
        if(openid!=null&&article!=null){
            if(fabulous1!=null&&fabulous1.getAid()==id){
                fabulousMapper.delete(wrapper);
                result.setCode(ResultCode.BUSINESS_NAME_EXISTED.code());
                return result;
            }else {
                fabulous.setAid(id);
                fabulous.setOpenid(openid);
                fabulousMapper.insert(fabulous);
                result.setCode(ResultCode.SUCCESS.code());
                return result;
            }
        }else {
            result.setCode(ResultCode.PERMISSION_TOKEN_EXPIRED
            .code());
            return result;
        }
    }
}
