package com.school.config;


import com.school.handlerInterceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


/**
 * 拦截器配置
 */

@Configuration
@EnableWebMvc
public class FilterConfig implements WebMvcConfigurer {

    @Autowired
   private MyInterceptor  myInterceptor;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/wx/**")
                .addPathPatterns("/comment/**")
                .addPathPatterns("/file/**")
                .addPathPatterns("/wx1/**")
                .excludePathPatterns("/wx/function")
                .excludePathPatterns("/wx/active")
                .excludePathPatterns("/wx/login")
                .excludePathPatterns("/")
                .excludePathPatterns("/index")
                .excludePathPatterns("/wx/gettoken");



    }

    private HandlerInterceptor myInterceptor() {
        return myInterceptor;
    }
}
