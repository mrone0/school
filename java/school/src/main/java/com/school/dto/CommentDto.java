package com.school.dto;

import com.school.config.LocalDateTimeSerializerConfig;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Date;

@Data
public class CommentDto {
    private Integer id;
    private Integer aid;
    private String openid;
    private String avatarurl;
    private String nickname;
    private String comment;
    private String image;
    private Date time;
    private String title;

    @SneakyThrows
    public String getTime() {
        LocalDateTimeSerializerConfig config = new LocalDateTimeSerializerConfig();
        String s = config.GeLin(time);
        return s;
    }

}
