package com.school.dto;

import com.school.config.LocalDateTimeSerializerConfig;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.util.Date;

@Data
public class ArticleDto {
    private Integer id;
    private String openid;
    private String title;
    private String content;
    private String image;
    private String tag;
    private Date createtime;
    private Date updatetime;
    private String avatarurl;
    private String nickname;


    @SneakyThrows
    public String getCreatetime() {
        LocalDateTimeSerializerConfig config =new LocalDateTimeSerializerConfig();
        String s = config.GeLin(createtime);
        return s;
    }

}
