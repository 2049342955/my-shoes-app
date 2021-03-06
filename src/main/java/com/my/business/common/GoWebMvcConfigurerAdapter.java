package com.my.business.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GoWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("resources/")
                .addResourceLocations("/static/")
                .addResourceLocations("public/")
                .addResourceLocations("META-INF/resources/");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //配置静态资源处理
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/")
//                .addResourceLocations("classpath:/META-INF/resources/");
//    }

}