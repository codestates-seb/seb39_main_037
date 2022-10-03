package com.main.project.securityConfig.provider;

import com.main.project.securityConfig.CheckReviewWriterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebserviceConfig implements WebMvcConfigurer {

    @Autowired
    private CheckReviewWriterInterceptor checkReviewWriterInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(checkReviewWriterInterceptor)
//                .addPathPatterns("/review/post")
//                .addPathPatterns("/review/edit")
//                .addPathPatterns("/review/delete/*");


    }



}
