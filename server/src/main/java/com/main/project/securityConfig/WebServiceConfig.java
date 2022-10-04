package com.main.project.securityConfig;

import com.main.project.securityConfig.interceptor.CheckCommentWriterInterceptor;
import com.main.project.securityConfig.interceptor.CheckReviewWriterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServiceConfig implements WebMvcConfigurer {

    @Autowired
    private CheckReviewWriterInterceptor checkReviewWriterInterceptor;

    @Autowired
    CheckCommentWriterInterceptor checkCommentWriterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(checkReviewWriterInterceptor)
                .addPathPatterns("/review/post/**")
                .addPathPatterns("/review/edit/**")
                .addPathPatterns("/review/delete/*");

        registry.addInterceptor(checkCommentWriterInterceptor)
                .excludePathPatterns("/comment/review/**")
                .addPathPatterns("/comment/post/**")
                .addPathPatterns("/comment/edit/**")
                .addPathPatterns("/comment/delete/**")
                .addPathPatterns("/mypage/**");

    }



}
