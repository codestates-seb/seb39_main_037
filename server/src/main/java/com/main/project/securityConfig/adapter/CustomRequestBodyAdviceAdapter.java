package com.main.project.securityConfig.adapter;

import com.main.project.review.dto.ReviewPostDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

@ControllerAdvice(annotations = RestController.class)
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Override
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage,
                                final MethodParameter parameter,
                                final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {

        System.out.printf("afterBodyRead ");
        if (body instanceof ReviewPostDto) {
            ReviewPostDto reqBody = (ReviewPostDto) body;
            System.out.printf(String.valueOf(reqBody.getUserId() +  reqBody.getFacilityStar()));
        }

        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage,
                                           final MethodParameter parameter,
                                           final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {
        System.out.println("beforeBodyRead");
        return inputMessage;
    }

    @Override
    public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage,
                                  final MethodParameter parameter, final Type targetType,
                                  final Class<? extends HttpMessageConverter<?>> converterType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(final MethodParameter methodParameter, final Type targetType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

}
